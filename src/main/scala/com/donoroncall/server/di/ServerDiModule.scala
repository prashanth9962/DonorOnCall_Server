package com.donoroncall.server.di

import com.donoroncall.server.connectors.MysqlClient
import com.donoroncall.server.rest.ServerInterface
import com.donoroncall.server.rest.controllers.authentication.{InMemorySessionHandler, SessionHandler}
import com.donoroncall.server.rest.undertow.UndertowApiServer
import com.google.inject.{AbstractModule, Binder, Module}
import com.typesafe.config.Config
import net.codingwell.scalaguice.ScalaModule

/**
  * Created by vishnu on 20/1/16.
  */
class ServerDiModule(config: Config) extends AbstractModule with ScalaModule {
  override def configure(): Unit = {

    bind[Config].toInstance(config)
    bind[MysqlClient].asInstanceOf[Singleton]

    config.getString("server.security.sessionHandler") match {
      case "InMemorySessionHandler" => bind[SessionHandler].to[InMemorySessionHandler].asInstanceOf[Singleton]
      case _ => bind[SessionHandler].to[InMemorySessionHandler].asInstanceOf[Singleton]
    }
    bind[ServerInterface].to[UndertowApiServer].asInstanceOf[Singleton]
  }
}
