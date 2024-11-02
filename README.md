# Akka Actor Modal

1. FSM (Finite State Machine) Actor Modal - see if we can use this for our use case.
2. Changing Behavior - in object oriented style, we used mutable state to change behavior.
3. In functional style, we will use FSM Actor Modal to change behavior or have multiple behaviors.
4. Stashing messages - when we are in a state where we cannot handle a message, we can stash it for later processing.