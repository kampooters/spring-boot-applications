package com.org.batch.algorithm;

/**
 * @author abdul.rehman4
 * @apiNote @{@link AlgorithmException} is custom exception only for {@link IAlgorithm} module
 */
public class AlgorithmException extends Exception {
    public AlgorithmException(String msg){
        super(msg);
    }
}
