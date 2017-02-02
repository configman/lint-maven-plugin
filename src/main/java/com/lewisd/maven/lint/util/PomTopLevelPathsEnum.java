package com.lewisd.maven.lint.util;

/**
 * Created by damon on 31/01/2017.
 */
public enum PomTopLevelPathsEnum {
  MODEL_VERSION("modelVersion"),
    PARENT("parent"),
    GROUP_ID("groupId"),
    ARTIFACT_ID("artifactId"),
    VERSION("version"),
    PACKAGING("packaging"),
    NAME("name"),
    DESCRIPTION("description"),
    URL("url"),
    INCEPTION_YEAR("inceptionYear"),
    ORGANISATION("organisation"),
    LICENSES("licenses"),
    DEVELOPERS("developers"),
    CONTRIBUTORS("contributors"),
    MAILING_LISTS("mailingLists"),
    PREREQUISITES("prerequisites"),
    MODULES("modules"),
    SCM("scm"),
    ISSUE_MANAGEMENT("issueManagament"),
    CI_MANAGEMENT("ciManagement"),
    DISTRIBUTION_MANAGEMENT("distributionManagement"),
    PROPERTIES("properties"),
    DEPENDENCY_MANAGEMENT("dependencyManagement"),
    DEPENDENCIES("dependencies"),
    REPOSITORIES("repositories"),
    PLUGIN_REPOSITORIES("pluginRepositories"),
    BUILD("build"),
    REPORTING("reporting"),
    PROFILES("profiles");

  private String value;

  PomTopLevelPathsEnum(final String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    return this.getValue();
  }

}
