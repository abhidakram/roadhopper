/*
 * Copyright (c) 2015 Andreas Wolf
 *
 * See te LICENSE file in the project root for further copyright information.
 */

package info.andreaswolf.roadhopper.simulation

import akka.actor.ActorRef
import info.andreaswolf.roadhopper.simulation.signals.{SignalState, Process}

import scala.collection.mutable
import scala.concurrent.Future


class SignalLogger(signalBus: ActorRef, val signalStates: mutable.Map[Int, SignalState]) extends Process(signalBus) {

	import context.dispatcher

	override def invoke(signals: SignalState): Future[Any] = Future {
		if (time % 500 == 0) {
			signalStates.put(time, signals)
			println(s"Logged ${signals.values.size} signal values at $time")
		}
	}

}
