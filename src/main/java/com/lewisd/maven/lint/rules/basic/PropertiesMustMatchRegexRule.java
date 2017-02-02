package com.lewisd.maven.lint.rules.basic;

import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.maven.model.InputLocation;
import org.apache.maven.model.Model;
import org.apache.maven.project.MavenProject;
import org.springframework.beans.factory.annotation.Autowired;

import com.lewisd.maven.lint.ResultCollector;
import com.lewisd.maven.lint.rules.AbstractRule;
import com.lewisd.maven.lint.util.ExpressionEvaluator;
import com.lewisd.maven.lint.util.ModelUtil;

public class PropertiesMustMatchRegexRule extends AbstractRule {

  private static String patternToMatchPropertyNames = "[a-zA-Z][-a-zA-Z.0-9]*[a-zA-Z0-9]";
  private static Pattern pattern = Pattern.compile(patternToMatchPropertyNames);

  @Autowired
  public PropertiesMustMatchRegexRule(final ExpressionEvaluator expressionEvaluator,
                                      final ModelUtil modelUtil) {
    super(expressionEvaluator, modelUtil);
  }

  @Override
  protected void addRequiredModels(final Set<String> requiredModels) {
    requiredModels.add(VERSION_PROPERTIES);
  }

  @Override
  public String getIdentifier() {
    return "PropertiesMustMatchRegex";
  }

  @Override
  public String getDescription() {
    return "By convention user defined properties must only contain lowercase and uppercase letters, " +
        "and perid '.' or dash '-'. ";
  }

  @Override
  public void invoke(final MavenProject mavenProject, final Map<String, Object> models,
                     final ResultCollector resultCollector) {

    Properties properties = mavenProject.getProperties();

    addpropertyNameViolationsToResultCollector(mavenProject, resultCollector, properties);
  }

  private void addpropertyNameViolationsToResultCollector(MavenProject mavenProject,
                                                          ResultCollector resultCollector,
                                                          Properties properties) {

    Model originalModel = mavenProject.getOriginalModel();
    final Map<Object, InputLocation> locations = modelUtil.getLocations(originalModel);

    InputLocation location = locations.get("properties");

    for (String propertyName : properties.stringPropertyNames()) {
      if (!pattern.matcher(propertyName).matches()) {
        resultCollector.addViolation(mavenProject, this, "Properties must match regex " +
            patternToMatchPropertyNames + " (" + propertyName + ")", location);
      }
    }
  }

}
