package com.zalf.prolog.api.enums;

import java.util.ArrayList;
import java.util.List;

import com.zalf.prolog.api.enums.objeto.DominioObject;

public enum DominioTipoMarcacao implements BaseEnum {
	
	MARCACAO_INICIO("Início"),
	MARCACAO_FIM("Término");

    private static final DominioTipoMarcacao[] ENUMS = DominioTipoMarcacao.values();

    private String descricao;

    private DominioTipoMarcacao(final String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public DominioObject getObject() {
        return DominioObject.builder().value(this.name()).label(this.descricao).build();
    }

    public static List objects() {
        final ArrayList<DominioObject> objects = new ArrayList<>();
        final DominioTipoMarcacao[] values = values();

        for (final DominioTipoMarcacao dominio : values) {
            objects.add(new DominioObject(dominio.name(), dominio.descricao));
        }

        return (List) objects;
    }

}
