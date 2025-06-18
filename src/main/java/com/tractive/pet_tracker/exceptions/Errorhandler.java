package com.tractive.pet_tracker.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

/* Error handler class for Global Exceptions */
@Data
@NoArgsConstructor
public class Errorhandler {

    private boolean success;
    private String message;

    
    public Errorhandler(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    



    
}
