package clases;

import java.util.ArrayList;
import java.util.List;

public class MainArticulos {
	
	
	public static void main(String[] args) {
		
		
		
		//Lista de artículos para luego cargar en cada ventana
		
		List<Articulo> listaArticulos = new ArrayList<>();
		//Creación de cada artículo
		//Camisas
		Camisa camisa1  = new Camisa(201, "Camisa beige con pechera estampada", Color.BEIGE, 89.90);
		Camisa camisa2  = new Camisa(202, "Camisa estampado boho tonos cálidos", Color.MULTICOLOR, 92.50);
		Camisa camisa3  = new Camisa(203, "Camisa estampado boho en tonos tierra", Color.MULTICOLOR, 95.00);
		Camisa camisa4  = new Camisa(204, "Camisa blanca manga larga de popelín", Color.BLANCO, 84.99);
		Camisa camisa5  = new Camisa(205, "Camisa vaquera con manga abullonada y lazo en la cintura", Color.AZUL, 89.00);
		Camisa camisa6  = new Camisa(206, "Camisa color rojo medio escote en V", Color.ROJO, 80.00);
		Camisa camisa7  = new Camisa(207, "Camisa estampado leopardo en tonos khaki", Color.KHAKI, 93.40);
		Camisa camisa8  = new Camisa(208, "Camisa estampada con volantes y hombro descubierto", Color.MULTICOLOR, 87.75);
		Camisa camisa9  = new Camisa(209, "Camisa verde caqui de manga francesa", Color.KHAKI, 82.60);
		Camisa camisa10 = new Camisa(210, "Camisa estampado boho vibrante", Color.MULTICOLOR, 90.80);
		Camisa camisa11 = new Camisa(211, "Camisa azul claro de popelín", Color.AZUL, 81.00);  
		Camisa camisa12 = new Camisa(212, "Camisa color marino escote en V", Color.AZUL, 88.00); 
		
		//Creación de pantalones
		Pantalon pantalon1  = new Pantalon(301, "Jean blanco roto", Color.BLANCO, 100.00);
		Pantalon pantalon2  = new Pantalon(302, "Pantalón de traje negro acampanado", Color.NEGRO, 129.90);
		Pantalon pantalon3  = new Pantalon(303, "Pantalón de traje beige", Color.BEIGE, 119.00);
		Pantalon pantalon4  = new Pantalon(304, "Pantalón jean negro", Color.NEGRO, 105.00);
		Pantalon pantalon5  = new Pantalon(305, "Jean azul clásico", Color.MULTICOLOR, 112.50);
		Pantalon pantalon6  = new Pantalon(306, "Jean azul lavado", Color.MULTICOLOR, 109.90);
		Pantalon pantalon7  = new Pantalon(307, "Pantalón fluido estampado", Color.MULTICOLOR, 123.00);
		Pantalon pantalon8  = new Pantalon(308, "Jean azul con rayas", Color.MULTICOLOR, 118.75);
		Pantalon pantalon9  = new Pantalon(309, "Jean negro con botones", Color.NEGRO, 107.80);
		Pantalon pantalon10 = new Pantalon(310, "Jean negro ancho", Color.NEGRO, 115.00);
		Pantalon pantalon11 = new Pantalon(311, "Jean acampanado negro", Color.NEGRO, 130.00);
		Pantalon pantalon12 = new Pantalon(312, "Pantalón fluido estampado estilo boho", Color.MULTICOLOR, 125.50);

		//Creación de jerséis
		Jersey jersey1  = new Jersey(401, "Jersey rayas asimétrico", Color.MULTICOLOR, 89.90);
		Jersey jersey2  = new Jersey(402, "Jersey beige clásico", Color.BEIGE, 85.00);
		Jersey jersey3  = new Jersey(403, "Jersey marrón punto angora", Color.MARRÓN, 95.50);
		Jersey jersey4  = new Jersey(404, "Jersey caqui cuello alto", Color.KHAKI, 90.00);
		Jersey jersey5  = new Jersey(405, "Jersey negro cuello alto", Color.NEGRO, 92.75);
		Jersey jersey6  = new Jersey(406, "Cárdigan punto calado beige", Color.BEIGE, 88.00);
		Jersey jersey7  = new Jersey(407, "Jersey punto rosa estilo alpaca", Color.ROSA, 94.50);
		Jersey jersey8  = new Jersey(408, "Jersey morado básico", Color.MORADO, 80.00);
		Jersey jersey9  = new Jersey(409, "Jersey gris básico", Color.GRIS, 82.50);
		Jersey jersey10 = new Jersey(410, "Jersey gris vuelo", Color.GRIS, 87.00);
		Jersey jersey11 = new Jersey(411, "Jersey cashmere beige", Color.BEIGE, 105.00);
		Jersey jersey12 = new Jersey(412, "Jersey alpaca gris", Color.GRIS, 99.90);

		//Creación de abrigos
		Abrigo abrigo1  = new Abrigo(501, "Blazer ante marrón", Color.MARRÓN, 400.00);
		Abrigo abrigo2  = new Abrigo(502, "Chaleco vaquero gris", Color.GRIS, 185.00);
		Abrigo abrigo3  = new Abrigo(503, "Chaqueta manga abullonada marrón", Color.MARRÓN, 220.00);
		Abrigo abrigo4  = new Abrigo(504, "Abrigo tipo trench corto azul marino", Color.AZUL, 410.00);
		Abrigo abrigo5  = new Abrigo(505, "Chaqueta vinilo morada", Color.MORADO, 400.00);
		Abrigo abrigo6  = new Abrigo(506, "Chaqueta estampada geométrica", Color.MULTICOLOR, 250.00);
		Abrigo abrigo7  = new Abrigo(507, "Abrigo largo gris jaspeado", Color.GRIS, 420.00);
		Abrigo abrigo8  = new Abrigo(508, "Abrigo marrón tipo batín", Color.MARRÓN, 400.00);
		Abrigo abrigo9  = new Abrigo(509, "Chaqueta beige de vestir", Color.BEIGE, 230.00);
		Abrigo abrigo10 = new Abrigo(510, "Abrigo marrón jaspeado con cinturón", Color.MARRÓN, 400.00);
		Abrigo abrigo11 = new Abrigo(511, "Chaqueta azul marino tipo chanel", Color.AZUL, 250.00);
		Abrigo abrigo12 = new Abrigo(512, "Abrigo marinero marrón", Color.MARRÓN, 400.00);

		//Creación de vestidos
		Vestido vestido1  = new Vestido(601, "Vestido manga larga fluido estampado", Color.MULTICOLOR, 200.00);
		Vestido vestido2  = new Vestido(602, "Vestido corto estampado", Color.MULTICOLOR, 190.00);
		Vestido vestido3  = new Vestido(603, "Vestido palabra de honor estampado con volantes", Color.MULTICOLOR, 210.00);
		Vestido vestido4  = new Vestido(604, "Vestido estampado largo", Color.MULTICOLOR, 220.00);
		Vestido vestido5  = new Vestido(605, "Vestido rojo con aberturas", Color.ROJO, 230.00);
		Vestido vestido6  = new Vestido(606, "Vestido negro con aberturas", Color.NEGRO, 230.00);
		Vestido vestido7  = new Vestido(607, "Vestido lencero estampado leopardo", Color.KHAKI, 250.00);
		Vestido vestido8  = new Vestido(608, "Vestido largo estampado boho", Color.MULTICOLOR, 270.00);
		Vestido vestido9  = new Vestido(609, "Vestido largo khaki punto", Color.KHAKI, 200.00);
		Vestido vestido10 = new Vestido(610, "Vestido vaquero con lazada", Color.MARRÓN, 210.00);
		Vestido vestido11 = new Vestido(611, "Vestido corto punto cuello alto beige", Color.BEIGE, 185.00);
		Vestido vestido12 = new Vestido(612, "Vestido corto punto cuello alto marrón", Color.MARRÓN, 185.00);

		//Creación de bolsos
		Bolso bolso1  = new Bolso(701, "Bolso flecos ante marrón", Color.MARRÓN, 310.00);
		Bolso bolso2  = new Bolso(702, "Bolso flecos ante marrón oscuro", Color.MARRÓN, 320.00);
		Bolso bolso3  = new Bolso(703, "Bolso flecos piel negro", Color.NEGRO, 350.00);
		Bolso bolso4  = new Bolso(704, "Bolso ante marrón alargado", Color.MARRÓN, 330.00);
		Bolso bolso5  = new Bolso(705, "Bolso marrón oscuro alargado ante", Color.MARRÓN, 340.00);
		Bolso bolso6  = new Bolso(706, "Bolso marrón piel alargado", Color.MARRÓN, 355.00);
		Bolso bolso7  = new Bolso(707, "Bolso marrón piel tipo saco", Color.MARRÓN, 360.00);
		Bolso bolso8  = new Bolso(708, "Bolso marrón piel tipo saco", Color.MARRÓN, 360.00);
		Bolso bolso9  = new Bolso(709, "Bolso beige rafia rayas", Color.BEIGE, 295.00);
		Bolso bolso10 = new Bolso(710, "Bolso marrón ante mini tipo saco", Color.MARRÓN, 310.00);
		Bolso bolso11 = new Bolso(711, "Bolso piel negro entrelazado", Color.NEGRO, 375.00);
		Bolso bolso12 = new Bolso(712, "Bolso de mano 'pajarita' vinilo granate", Color.ROJO, 395.00);

		//Creación de calzado
		Calzado calzado1  = new Calzado(801, "Sandalia romana ante marrón", Color.MARRÓN, 220.00);
		Calzado calzado2  = new Calzado(802, "Bota flecos ante marrón", Color.MARRÓN, 240.00);
		Calzado calzado3  = new Calzado(803, "Bota campera marrón ante", Color.MARRÓN, 250.00);
		Calzado calzado4  = new Calzado(804, "Botín ante marrón claro", Color.MARRÓN, 230.00);
		Calzado calzado5  = new Calzado(805, "Bota ante marrón plana", Color.MARRÓN, 220.00);
		Calzado calzado6  = new Calzado(806, "Bota estilo india flecos ante marrón", Color.MARRÓN, 260.00);
		Calzado calzado7  = new Calzado(807, "Bota alta ante marrón", Color.MARRÓN, 280.00);
		Calzado calzado8  = new Calzado(808, "Bota piel militar negra", Color.NEGRO, 300.00);
		Calzado calzado9  = new Calzado(809, "Deportivas flecos marrón", Color.MARRÓN, 210.00);
		Calzado calzado10 = new Calzado(810, "Cuña camel", Color.MARRÓN, 220.00);
		Calzado calzado11 = new Calzado(811, "Bailarinas ganchillo rayas", Color.MULTICOLOR, 200.00);
		Calzado calzado12 = new Calzado(812, "Sandalia tacón negra", Color.NEGRO, 250.00);

		// array list con todos los artículos
		Articulo[] articulos = {
		    // CAMISAS
		    camisa1, camisa2, camisa3, camisa4, camisa5, camisa6, camisa7, camisa8, camisa9, camisa10, camisa11, camisa12,
		    
		    // PANTALONES
		    pantalon1, pantalon2, pantalon3, pantalon4, pantalon5, pantalon6, pantalon7, pantalon8, pantalon9, pantalon10, pantalon11, pantalon12,
		    
		    // JERSÉIS
		    jersey1, jersey2, jersey3, jersey4, jersey5, jersey6, jersey7, jersey8, jersey9, jersey10, jersey11, jersey12,
		    
		    // ABRIGOS
		    abrigo1, abrigo2, abrigo3, abrigo4, abrigo5, abrigo6, abrigo7, abrigo8, abrigo9, abrigo10, abrigo11, abrigo12,
		    
		    // VESTIDOS
		    vestido1, vestido2, vestido3, vestido4, vestido5, vestido6, vestido7, vestido8, vestido9, vestido10, vestido11, vestido12,
		    
		    // BOLSOS
		    bolso1, bolso2, bolso3, bolso4, bolso5, bolso6, bolso7, bolso8, bolso9, bolso10, bolso11, bolso12,
		    
		    // CALZADO
		    calzado1, calzado2, calzado3, calzado4, calzado5, calzado6, calzado7, calzado8, calzado9, calzado10, calzado11, calzado12
		};

		// añadimos todos los artículos a la lista
		for(Articulo a : articulos){
		    listaArticulos.add(a);
		}

	}
}
