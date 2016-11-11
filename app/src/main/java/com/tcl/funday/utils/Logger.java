package com.tcl.funday.utils;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

/**
 * @author Liyang Sun
 * @Description:
 * @date 2016/11/11 20:01
 * @copyright HAWK
 */

public class Logger {
    public static final String TAG = "Logger";
    public static boolean DEBUG = true;

    public Logger() {
    }

    public static void v(Object o) {
        if(DEBUG) {
            String log = toJson(o);
            Log.v("Logger", log);
            Logger2File.log2File("Logger", log);
        }

    }

    public static void v(String tag, Object o) {
        if(DEBUG) {
            String log = toJson(o);
            Log.v(tag, log);
            Logger2File.log2File(tag, log);
        }

    }

    public static void v(String tag, String msg, Throwable tr) {
        if(DEBUG) {
            String log = msg + '\n' + getStackTraceString(tr);
            Log.v(tag, log);
            Logger2File.log2File(tag, log);
        }

    }

    public static void v(String tag, String format, Object... args) {
        if(DEBUG) {
            String log = String.format(format, args);
            Log.v(tag, log);
            Logger2File.log2File(tag, log);
        }

    }

    public static void d(Object o) {
        if(DEBUG) {
            String log = toJson(o);
            Log.d("Logger", log);
            Logger2File.log2File("Logger", log);
        }

    }

    public static void d(String tag, Object o) {
        if(DEBUG) {
            String log = toJson(o);
            Log.d(tag, log);
            Logger2File.log2File(tag, log);
        }

    }

    public static void d(String tag, String msg, Throwable tr) {
        if(DEBUG) {
            String log = msg + '\n' + getStackTraceString(tr);
            Log.d(tag, log);
            Logger2File.log2File(tag, log);
        }

    }

    public static void d(String tag, String format, Object... args) {
        if(DEBUG) {
            String log = String.format(format, args);
            Log.d(tag, log);
            Logger2File.log2File(tag, log);
        }

    }

    public static void i(Object o) {
        if(DEBUG) {
            String log = toJson(o);
            Log.i("Logger", log);
            Logger2File.log2File("Logger", log);
        }

    }

    public static void i(String tag, Object o) {
        if(DEBUG) {
            String log = toJson(o);
            Log.i(tag, log);
            Logger2File.log2File(tag, log);
        }

    }

    public static void i(String tag, String msg, Throwable tr) {
        if(DEBUG) {
            String log = msg + '\n' + getStackTraceString(tr);
            Log.i(tag, log);
            Logger2File.log2File(tag, log);
        }

    }

    public static void i(String tag, String format, Object... args) {
        if(DEBUG) {
            String log = String.format(format, args);
            Log.i(tag, log);
            Logger2File.log2File(tag, log);
        }

    }

    public static void w(Object o) {
        if(DEBUG) {
            String log = toJson(o);
            Log.w("Logger", log);
            Logger2File.log2File("Logger", log);
        }

    }

    public static void w(String tag, Object o) {
        if(DEBUG) {
            String log = toJson(o);
            Log.w(tag, log);
            Logger2File.log2File(tag, log);
        }

    }

    public static void w(String tag, String msg, Throwable tr) {
        if(DEBUG) {
            String log = msg + '\n' + getStackTraceString(tr);
            Log.w(tag, log);
            Logger2File.log2File(tag, log);
        }

    }

    public static void w(String tag, String format, Object... args) {
        if(DEBUG) {
            String log = String.format(format, args);
            Log.w(tag, log);
            Logger2File.log2File(tag, log);
        }

    }

    public static void e(Object o) {
        if(DEBUG) {
            String log = toJson(o);
            Log.e("Logger", log);
            Logger2File.log2File("Logger", log);
        }

    }

    public static void e(String tag, Object o) {
        if(DEBUG) {
            String log = toJson(o);
            Log.e(tag, log);
            Logger2File.log2File(tag, log);
        }

    }

    public static void e(String tag, String msg, Throwable tr) {
        if(DEBUG) {
            String log = msg + '\n' + getStackTraceString(tr);
            Log.e(tag, log);
            Logger2File.log2File(tag, log);
        }

    }

    public static void e(String tag, String format, Object... args) {
        if(DEBUG) {
            String log = String.format(format, args);
            Log.e(tag, log);
            Logger2File.log2File(tag, log);
        }

    }

    public static void sysout(String msg) {
        try {
            Log.v("Logger", msg);
            Logger2File.log2File("Logger", msg);
        } catch (Throwable var2) {
            ;
        }

    }

    public static void printExc(Class<?> clazz, Throwable e) {
        try {
            if(DEBUG) {
                e.printStackTrace();
                Logger2File.log2File("Logger", e);
            } else {
                String ee = clazz == null?"Unknow":clazz.getSimpleName();
                Log.v("Logger", String.format("class[%s], %s", new Object[]{ee, e + ""}));
            }
        } catch (Throwable var3) {
            var3.printStackTrace();
        }

    }

    public static String toJson(Object msg) {
        if(msg instanceof String) {
            return msg.toString();
        } else {
            String json = JSON.toJSONString(msg);
            if(json.length() > 500) {
                json = json.substring(0, 500);
            }

            return json;
        }
    }

    static String getStackTraceString(Throwable tr) {
        if(tr == null) {
            return "";
        } else {
            for(Throwable t = tr; t != null; t = t.getCause()) {
                if(t instanceof UnknownHostException) {
                    return "";
                }
            }

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            tr.printStackTrace(pw);
            pw.flush();
            return sw.toString();
        }
    }
}
