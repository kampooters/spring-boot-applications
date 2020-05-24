package com.org.batch.algorithm;


/**
 * @author abdul.rehman4
 * @apiNote @{@link IAlgorithm} provides the abstraction for Algorithm where we
 * can do our processing related to the CSv. If you want different processing for CSV then write you own
 * implementation for the interface adn annotate with @{@link org.springframework.stereotype.Service} annotation
 */
public interface IAlgorithm {

    /**
     * perform teh algo processing for submitted data
     * @param objectList @{@link java.util.List} of object wich can be cast to desired object
     * @return @{@link Object} or any desired Object
     * @throws AlgorithmException
     */
    Object calculate(Object objectList) throws AlgorithmException;

    /**
     * Returns the calculated stats
     * @return any desired @{@link Object}
     */
    Object getStats();

    /**
     * Flushes the all stats stored in algorithm during the processing
     * @throws AlgorithmException
     */
    void flush() throws AlgorithmException;

    /**
     * Sets metadata for algo which it will use during processing
     * @param metadata
     */
    void setMetaData(Object metadata);
}
