package com.vortexbird.pusay.modelo.control.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vortexbird.pusay.modelo.PsyPais;
import com.vortexbird.pusay.modelo.control.IPsyPaisLogic;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestCrearPais {
	
	
	@Autowired
	IPsyPaisLogic psyPaisLogic;

	@Test
	@Rollback(false)
	public void test()throws Exception {
		PsyPais psyPais=new PsyPais();
		psyPais.setNombre("PRUEBA");
		psyPais.setEstadoRegistro("A");
		
		psyPaisLogic.savePsyPais(psyPais);
		
	}

}
