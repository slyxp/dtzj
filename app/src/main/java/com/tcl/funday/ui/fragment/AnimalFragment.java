package com.tcl.funday.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cundong.recyclerview.EndlessRecyclerOnScrollListener;
import com.cundong.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.cundong.recyclerview.RecyclerViewUtils;
import com.tcl.funday.R;
import com.tcl.funday.support.ItemModel;
import com.tcl.funday.support.LoadingFooter;
import com.tcl.funday.support.SampleHeader;
import com.tcl.funday.utils.NetworkUtils;
import com.tcl.funday.utils.RecyclerViewStateUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * @author Liyang Sun
 * @Description: "动物"页面
 * @date 2016/11/7 19:49
 * @copyright HAWK
 */

public class AnimalFragment extends Fragment {

    /**服务器端一共多少条数据*/
    private static final int TOTAL_COUNTER = 64;

    /**每一页展示多少条数据*/
    private static final int REQUEST_COUNT = 10;

    /**已经获取到多少条数据了*/
    private int mCurrentCounter = 0;

    private RecyclerView mRecyclerView;

    private DataAdapter mDataAdapter;

    private PreviewHandler mHandler = new PreviewHandler(this);
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View animalView = inflater.inflate(R.layout.fragment_animal, container, false);

        initView(animalView);
        initData();

        return animalView;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);
    }

    private void initData() {
        //init data
        ArrayList<ItemModel> dataList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            ItemModel item = new ItemModel();
            item.id = i;
            item.title = "item" + i;
            dataList.add(item);
        }

        mCurrentCounter = dataList.size();

        mDataAdapter = new DataAdapter(getContext());
        mDataAdapter.addItems(dataList);

        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mDataAdapter);
        mRecyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        RecyclerViewUtils.setHeaderView(mRecyclerView, new SampleHeader(getContext()));

        mRecyclerView.addOnScrollListener(mOnScrollListener);
    }

    private void notifyDataSetChanged() {
        mHeaderAndFooterRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void addItems(ArrayList<ItemModel> list) {

        mDataAdapter.addItems(list);
        mCurrentCounter += list.size();
    }

    private EndlessRecyclerOnScrollListener mOnScrollListener = new EndlessRecyclerOnScrollListener() {

        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);

            LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(mRecyclerView);
            if(state == LoadingFooter.State.Loading) {
                Log.d("@Cundong", "the state is Loading, just wait..");
                return;
            }

            if (mCurrentCounter < TOTAL_COUNTER) {
                // loading more
                RecyclerViewStateUtils.setFooterViewState(getActivity(), mRecyclerView, REQUEST_COUNT, LoadingFooter.State.Loading, null);
                requestData();
            } else {
                //the end
                RecyclerViewStateUtils.setFooterViewState(getActivity(), mRecyclerView, REQUEST_COUNT, LoadingFooter.State.TheEnd, null);
            }
        }
    };

    private class PreviewHandler extends Handler {

        private WeakReference<AnimalFragment> ref;

        PreviewHandler(AnimalFragment fragment) {
            ref = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            final AnimalFragment fragment = ref.get();
            if (fragment.getActivity() == null || fragment.getActivity().isFinishing()) {
                return;
            }

            switch (msg.what) {
                case -1:
                    int currentSize = mDataAdapter.getItemCount();

                    //模拟组装10个数据
                    ArrayList<ItemModel> newList = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        if (newList.size() + currentSize >= TOTAL_COUNTER) {
                            break;
                        }

                        ItemModel item = new ItemModel();
                        item.id = currentSize + i;
                        item.title = "item" + (item.id);

                        newList.add(item);
                    }

                    addItems(newList);
                    RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.Normal);
                    break;
                case -2:
                    notifyDataSetChanged();
                    break;
                case -3:
                    RecyclerViewStateUtils.setFooterViewState(getActivity(), mRecyclerView, REQUEST_COUNT, LoadingFooter.State.NetWorkError, mFooterClick);
                    break;
            }
        }
    }

    private View.OnClickListener mFooterClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerViewStateUtils.setFooterViewState(getActivity(), mRecyclerView, REQUEST_COUNT, LoadingFooter.State.Loading, null);
            requestData();
        }
    };

    /**
     * 模拟请求网络
     */
    private void requestData() {

        new Thread() {

            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //模拟一下网络请求失败的情况
                if(NetworkUtils.isNetAvailable(getContext())) {
                    mHandler.sendEmptyMessage(-1);
                } else {
                    mHandler.sendEmptyMessage(-3);
                }
            }
        }.start();
    }

    private class DataAdapter extends RecyclerView.Adapter {

        private LayoutInflater mLayoutInflater;
        private SortedList<ItemModel> mSortedList;

        public DataAdapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
            mSortedList = new SortedList<>(ItemModel.class, new SortedList.Callback<ItemModel>() {

                /**
                 * 返回一个负整数（第一个参数小于第二个）、零（相等）或正整数（第一个参数大于第二个）
                 */
                @Override
                public int compare(ItemModel o1, ItemModel o2) {

                    if (o1.id < o2.id) {
                        return -1;
                    } else if (o1.id > o2.id) {
                        return 1;
                    }

                    return 0;
                }

                @Override
                public boolean areContentsTheSame(ItemModel oldItem, ItemModel newItem) {
                    return oldItem.title.equals(newItem.title);
                }

                @Override
                public boolean areItemsTheSame(ItemModel item1, ItemModel item2) {
                    return item1.id == item2.id;
                }

                @Override
                public void onInserted(int position, int count) {
                    notifyItemRangeInserted(position, count);
                }

                @Override
                public void onRemoved(int position, int count) {
                    notifyItemRangeRemoved(position, count);
                }

                @Override
                public void onMoved(int fromPosition, int toPosition) {
                    notifyItemMoved(fromPosition, toPosition);
                }

                @Override
                public void onChanged(int position, int count) {
                    notifyItemRangeChanged(position, count);
                }
            });
        }

        public void addItems(ArrayList<ItemModel> list) {
            mSortedList.beginBatchedUpdates();

            for(ItemModel itemModel : list) {
                mSortedList.add(itemModel);
            }

            mSortedList.endBatchedUpdates();
        }

        public void deleteItems(ArrayList<ItemModel> items) {
            mSortedList.beginBatchedUpdates();
            for (ItemModel item : items) {
                mSortedList.remove(item);
            }
            mSortedList.endBatchedUpdates();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mLayoutInflater.inflate(R.layout.common_parent_item_rectangle, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            ItemModel item = mSortedList.get(position);

            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.textView.setText(item.title);
        }

        @Override
        public int getItemCount() {
            return mSortedList.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder {

            private TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.info_text);

                textView.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ItemModel item = mSortedList.get(RecyclerViewUtils.getAdapterPosition(mRecyclerView, ViewHolder.this));
                        Toast.makeText(getContext(), item.title, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}
