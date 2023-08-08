package com.boardgameApp.Controller;

import com.boardgameApp.Domain.*;
import com.boardgameApp.Repositories.*;
import com.boardgameApp.Service.BoardgameAppServices;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

class StartedGameServiceTest {

    private BoardgameAppServices sut;

    @BeforeEach
    public void setUp() {
        GameRepository gameRepository = mock(GameRepository.class);
        UserRepository userRepository = mock(UserRepository.class);

        User user = mock(User.class);
        when(userRepository.getById("Gemma")).thenReturn(user);

        Game game = mock(Game.class);
        when(gameRepository.getById("1234L")).thenReturn(game);


        sut = new BoardgameAppServices(userRepository, gameRepository);
    }


}