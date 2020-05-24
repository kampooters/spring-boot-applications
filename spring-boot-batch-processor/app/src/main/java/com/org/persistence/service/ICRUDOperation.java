package com.org.persistence.service;

import com.org.dto.HttpResonseDTO;

/**
 * @author abdul.rehman4
  * @version 1.0
 * @since v1.0
 * {@link ICRUDOperation} provides interface for business model
 */
public interface ICRUDOperation {
      /**
       * Returns the all objects available
       * @return {@link HttpResonseDTO}
       */
      HttpResonseDTO getAll();

      /**
       * Returns record matching to ID
       * @param id
       * @return ResponseDTO
       */
      HttpResonseDTO getById(String id);

      /**
       * Adds Object
       * @param obj
       */
      HttpResonseDTO insert(Object obj);

      /**
       * updates the Object
       * @param object
       */
      HttpResonseDTO update(Object object);

      /**
       * Deletes the Object by ID
       * @param object
       */
      HttpResonseDTO delete(Object object);

      HttpResonseDTO search(Object object);
}
