package se705.bisimulation.lts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Process {
	private HashMap<String, HashMap<String, List<String>>> _transitions;
	private List<String> _states;
	private List<String> _actions;
	private String _currentState;
	
	public Process() {
		_transitions = new HashMap<String, HashMap<String, List<String>>>();
		_states = new ArrayList<String>();
		_actions = new ArrayList<String>();
	}
	
	public Process(String initialState) {
		this();
		this._currentState = initialState;
	}
	
	public Process addState(String state) {
		this._states.add(state);
		return this;
	}
	
	public Process addAction(String action) {
		this._actions.add(action);
		return this;
	}
	
	public Process addTransition(String source, String action, String destination) {
		if (!_transitions.containsKey(source)) {
			this._transitions.put(source, new HashMap<String,List<String>>());
		}
		
		HashMap<String,List<String>> transitionsFromSource = _transitions.get(source);
		
		if (!transitionsFromSource.containsKey(action)) {
			transitionsFromSource.put(action, new ArrayList<String>());
		}
		
		transitionsFromSource.get(action).add(destination);
		
		return this;
	}
	
	public List<String> doAction(String action) {
		return this._transitions.get(_currentState).get(action);
	}
	
	public Process setCurrentState(String state) {
		if (!this._states.contains(state)) {
			throw new IllegalArgumentException("State " + state + " does not exist in this process");
		}
		
		this._currentState = state;
		return this;
	}
}
