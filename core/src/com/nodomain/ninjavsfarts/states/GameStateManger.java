package com.nodomain.ninjavsfarts.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManger {
    private Stack<state> states;

    public GameStateManger(){
        states = new Stack<state>();
    }

    public void push(state state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void set(state state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
