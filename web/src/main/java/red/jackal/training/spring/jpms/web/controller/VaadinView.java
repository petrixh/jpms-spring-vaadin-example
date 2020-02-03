package red.jackal.training.spring.jpms.web.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.HasValue.ValueChangeListener;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.ui.LoadMode;

import red.jackal.training.spring.jpms.entity.ExampleEntity;
import red.jackal.training.spring.jpms.service.ExampleService;

@Route("")
@JavaScript(value = "./src/test.js", loadMode = LoadMode.EAGER)
public class VaadinView extends VerticalLayout {

	private final ExampleService exampleService;
	private ListDataProvider<ExampleEntity> exampleEntityListDataProvider;

	@Autowired
	public VaadinView(ExampleService exampleService) {
		this.exampleService = exampleService;

		add(new Html("<div> Hello from Modular app in module: " + VaadinView.class.getModule().getName() + "</div>"));
		add(new Html("<div> "+ getModulesHello() + "</div>"));
		add(new Html("<div> "+ getVaadinDepsHello() + "</div>"));

		Grid<ExampleEntity> exampleEntityGrid = new Grid<>(ExampleEntity.class);
		exampleEntityGrid.setWidthFull();
		exampleEntityGrid.setHeight("200px");

		TextField textField = new TextField("Type something and hit Enter to add to the DB and refresh the Grid");
		textField.setWidth("500px");
		textField.addValueChangeListener(new ValueChangeListener<ComponentValueChangeEvent<TextField, String>>() {
			@Override
			public void valueChanged(ComponentValueChangeEvent<TextField, String> event) {
				System.out.println("Value change for TextField: " + event.getValue());
				if (event.getValue() != null && !"".equalsIgnoreCase(event.getValue().trim())) {
					exampleService.addExample(event.getValue().trim());
					reloadGrid();
				}
			}
		});

		add(textField);

		exampleEntityListDataProvider = new ListDataProvider<ExampleEntity>(new ArrayList<>());
		exampleEntityGrid.setDataProvider(exampleEntityListDataProvider);

		add(exampleEntityGrid);
	}

	public void reloadGrid() {
		exampleEntityListDataProvider.getItems().clear();
		exampleEntityListDataProvider.getItems().addAll(exampleService.getAllExamples());
		exampleEntityListDataProvider.refreshAll();
	}

	public String getVaadinDepsHello() {
		return exampleService.getClass().getName() + " from module: " + exampleService.getClass().getModule().getName();
	}

	public String getModulesHello() {
		return ExampleEntity.class.getName() + " from module: " + ExampleEntity.class.getModule().getName();
	}
}
