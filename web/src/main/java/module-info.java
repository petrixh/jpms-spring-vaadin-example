module example.web {
    requires example.persistence;
    requires example.service;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.beans;
    requires spring.web;

    //Vaadin UI components used
    requires flow.server;
    requires vaadin.grid.flow;
    requires flow.data;
    requires vaadin.text.field.flow;
    requires vaadin.lumo.theme;
    requires vaadin.ordered.layout.flow;

    // Allow spring and Vaadin to read our web module UI packages
    opens red.jackal.training.spring.jpms.web to spring.core, spring.beans, spring.context, flow.server, vaadin.spring;
    opens red.jackal.training.spring.jpms.web.controller to spring.beans, spring.web, flow.server, vaadin.spring;
    exports red.jackal.training.spring.jpms.web to flow.server,vaadin.spring;
    exports red.jackal.training.spring.jpms.web.controller to flow.server,vaadin.spring;
}