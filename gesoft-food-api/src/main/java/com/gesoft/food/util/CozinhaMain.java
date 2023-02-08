
package com.gesoft.food.util;

public class CozinhaMain {
	public static void main(String[] args) {
		System.out.print("Done");
	}
		/*ApplicationContext applicationContext = new SpringApplicationBuilder(GesoftFoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Brasileira");
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Japonesa");
		cozinhaRepository.salvarAtualizar(cozinha);
		cozinhaRepository.salvarAtualizar(cozinha2);

		List<Cozinha> listCoz = cozinhaRepository.listar();
		for (Cozinha c : listCoz) {
			if (c.getId() == 1L) {
				cozinhaRepository.remover(c);
			} else {
				Cozinha c2 = cozinhaRepository.buscarPorId(c.getId());
				System.out.printf("%d - %s\n", c2.getId(), c2.getNome());
			}

		}
	}*/
}
