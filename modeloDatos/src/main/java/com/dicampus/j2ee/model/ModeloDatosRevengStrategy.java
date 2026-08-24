package com.dicampus.j2ee.model;

import org.hibernate.cfg.reveng.DelegatingReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.ReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.TableIdentifier;

public class ModeloDatosRevengStrategy extends DelegatingReverseEngineeringStrategy {

    public final String ENTITY_SUFFIX = "Entity";

    public ModeloDatosRevengStrategy(ReverseEngineeringStrategy delegate) {
        super(delegate);
    }

    @Override
    public String tableToClassName(TableIdentifier tableIdentifier) {
        String tableName = super.tableToClassName(tableIdentifier);
        String className = tableIdentifier.getName().substring(0, 1).toUpperCase()
                .concat(tableIdentifier.getName().substring(1, tableIdentifier.getName().length()).toLowerCase());
        return tableName.replaceAll(tableIdentifier.getName(), className).concat(ENTITY_SUFFIX);
    }

    @Override
    public String columnToPropertyName(TableIdentifier tableIdentifier, String name) {
        return super.columnToPropertyName(tableIdentifier, name).substring(3).toLowerCase();
    }
}