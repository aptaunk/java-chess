package personal.javachess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaChessApplication implements CommandLineRunner {

	@Autowired
	private Game game;

	public static void main(String[] args) {
		SpringApplication.run(JavaChessApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		game.run();
	}

}
