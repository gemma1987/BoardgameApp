package com.boardgameApp.Controller;

import com.boardgameApp.Domain.*;
import com.boardgameApp.Repositories.*;
import com.boardgameApp.Service.BoardgameAppServices;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

class MyGameServiceTest {

    private BoardgameAppServices sut;

    @BeforeEach
    public void setUp() {
        GameRepository gameRepository = mock(GameRepository.class);
        PersonRepository userRepository = mock(PersonRepository.class);

        Person person = mock(Person.class);
        when(userRepository.getById(1)).thenReturn(person);

        Game game = mock(Game.class);
        when(gameRepository.getById(1234L)).thenReturn(game);


        sut = new BoardgameAppServices(userRepository, gameRepository);
    }


}