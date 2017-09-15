package com.bip.base.utils;

/**
 * Created by www.bip-soft.com on 2017/3/16.
 */
import org.apache.log4j.Logger;

import java.io.Closeable;



public abstract class SerializeTranscoder{

    protected static Logger logger = Logger.getLogger(SerializeTranscoder.class);

    public abstract byte[] serialize(Object value);

    public abstract Object deserialize(byte[] in);

    public void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                logger.info("Unable to close " + closeable, e);
            }
        }
    }
}