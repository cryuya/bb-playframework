name := """bb-playframework"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.8"

libraryDependencies += guice
libraryDependencies += "com.h2database" % "h2" % "1.4.192"