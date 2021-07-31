package org.flayart.eternal.scorebaord;

import lombok.Getter;

@Getter
public class BoardProvider {
    
    private final IBoard iBoard;
    
    public BoardProvider(IBoard iBoard) {
        this.iBoard = iBoard;
    }
}
