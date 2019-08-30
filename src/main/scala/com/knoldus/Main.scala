package com.knoldus

import akka.NotUsed
import akka.actor.{ActorSystem, Props}
import akka.persistence.query.{EventEnvelope, PersistenceQuery}
import akka.persistence.query.journal.leveldb.scaladsl.LeveldbReadJournal
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source


object Persistent extends App {
  import Counter._

  val system = ActorSystem("persistent-actors")
  implicit val mat = ActorMaterializer()(system)

  val queries = PersistenceQuery(system).readJournalFor[LeveldbReadJournal](
    LeveldbReadJournal.Identifier
  )

  val evts: Source[EventEnvelope, NotUsed] =
    queries.eventsByPersistenceId("counter-example")

  evts.runForeach { evt => println(s"Event $evt")}

  val counter = system.actorOf(Props[Counter])

  counter ! Command(Increment(3))

  counter ! Command(Increment(5))

  counter ! Command(Decrement(3))

  counter ! "print"

  Thread.sleep(1000)

  system.terminate()

}
