package com.jaredsburrows.license.internal.report.json

import com.jaredsburrows.license.internal.Project
import groovy.json.JsonOutput

/**
 * @author <a href="mailto:jaredsburrows@gmail.com">Jared Burrows</a>
 */
final class JsonReport {
  final List<Project> projects
  final def jsonArray = []

  JsonReport(projects) {
    this.projects = projects
  }

  def jsonArray() {
    // Create new license object for each project
    projects.each { project ->
      final def jsonReportObject = JsonReportObject.builder()
        .name(project.name)
        .developers(project.developers)
        .url(project.url)
        .year(project.year)
        .license(project.license)
        .build()
        .jsonObject()

      jsonArray.add(jsonReportObject)
    }

    jsonArray
  }

  def toJson() {
    JsonOutput.toJson(jsonArray())
  }
}