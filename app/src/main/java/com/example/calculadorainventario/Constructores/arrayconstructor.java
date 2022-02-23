package com.example.calculadorainventario.Constructores;

import androidx.annotation.NonNull;

public class arrayconstructor {
    Double UnidadesMaterial;

    public arrayconstructor() {
    }

    public Double getUnidadesMaterial() {
        return UnidadesMaterial;
    }

    public void setUnidadesMaterial(Double unidadesMaterial) {
        UnidadesMaterial = unidadesMaterial;
    }

    @NonNull
    @Override
    public String toString() {
        return String.valueOf(UnidadesMaterial);
    }
}
