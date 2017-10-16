package darwin;
import static org.junit.Assert.*;

import org.junit.Test;

public class IndividualTest {

	@Test 
	public void randomIndivtest(){
		Individual miterio = new Individual(5);
		assertEquals(miterio.genes.length, 5);
		
	}
	@Test
	public void sexTest() {
		Individual mommy = new Individual("maria".toCharArray());
		Individual daddy = new Individual("pedro".toCharArray());
		
		Individual kiddo = mommy.sex(daddy, 0.01);
		assertNotEquals(kiddo.getGenesStr(), mommy.getGenesStr());
		assertNotEquals(kiddo.getGenesStr(), daddy.getGenesStr());
	}
	
	@Test
	public void mutateTest(){
		Individual pedro = new Individual("pedro".toCharArray());
		char[] fetus = pedro.crossover(pedro);
		
		char[] mutation = pedro.mutate(fetus, 1);
		
		for (int genIndex = 0; genIndex < pedro.genes.length; genIndex++){
			assertTrue(Character.isLetter(mutation[genIndex]));
		}
		System.out.println(pedro.genes);
		System.out.println(mutation);
		assertNotEquals(pedro.getGenesStr(), String.valueOf(mutation));
		// note: can be sometimes be true, if no mutation ocurrs
	}
	
	@Test
	public void crossoverTest(){
		Individual mommy = new Individual("maria".toCharArray());
		Individual daddy = new Individual("pedro".toCharArray());
		char[] fetus = daddy.crossover(mommy);
		
		for (int genIndex = 0; genIndex < daddy.genes.length; genIndex++){
			assertTrue(fetus[genIndex] == mommy.genes[genIndex] || 
					fetus[genIndex] == daddy.genes[genIndex]);
		}
		fetus = daddy.crossover(daddy);
		
		for (int genIndex = 0; genIndex < daddy.genes.length; genIndex++){
			assertTrue(fetus[genIndex] == daddy.genes[genIndex]);
		}
		
		
	}
}
