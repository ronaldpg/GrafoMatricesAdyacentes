package GrafoMatricesA;
import java.util.Scanner;
import javax.swing.JOptionPane;
public class GrafoM {

	int numVerts;
	static int MaxVerts = 20; 
	Vertices [] verts;
	int [][] matAd;

	public GrafoM(){
		this(MaxVerts);
	}
	public GrafoM(int mx){
		matAd = new int [mx][mx];
		verts = new Vertices[mx];
		for (int i = 0; i < mx; i++)
			for (int j = 0; i < mx; i++)
				matAd[i][j] = 0;
		numVerts = 0;
	}

	public void nuevoVertice (String nom){
		boolean esta = numVertice(nom) >= 0;
		if (!esta) {
			Vertices v = new Vertices(nom); 
			v.asigVert(numVerts);
			verts[numVerts++] = v;
		}
	}
	public int numVertice(String vs){
		Vertices v = new Vertices(vs);
		boolean encontrado = false;
		int i = 0;
		for (; (i < numVerts) && !encontrado;){
			encontrado = verts[i].equals(v);
			if (!encontrado) i++ ; 
		}
		return (i < numVerts) ? i : -1 ;
	}
	//agregar una nueva arista entre dos vertices por su nombre
	public void nuevoArco(String a, String b)throws Exception{
		int va, vb;
		va = numVertice(a);
		vb = numVertice(b);
		if (va < 0 || vb < 0) throw new Exception ("Vértice no existe");
		matAd[va][vb] = 1;
	}
	//agregar una nueva arista entre dos vertices por su indice
	public void nuevoArco(int va, int vb)throws Exception{
		if (va < 0 || vb < 0) throw new Exception ("Vértice no existe");
		matAd[va][vb] = 1;
	}
	//verificar si dos vertices son adyacentes por su nombre
	public boolean adyacente(String a, String b)throws Exception{
		int va, vb;
		va = numVertice(a);
		vb = numVertice(b);
		if (va < 0 || vb < 0) throw new Exception ("Vértice no existe");
		return matAd[va][vb] == 1;
	}
	//verificar si dos vertices son adyacentes por su indice
	public boolean adyacente(int va, int vb)throws Exception{
		if (va < 0 || vb < 0) throw new Exception ("Vértice no existe");
		return matAd[va][vb] == 1;
	}

	public void pedirVerticesAlUsuario() {
		Scanner scanner = new Scanner(System.in);
		int numVerticesIngresar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de vértices que desea agregar (máximo 20):"));

		while (numVerticesIngresar > MaxVerts || numVerticesIngresar <= 0) {
			JOptionPane.showMessageDialog(null,"El número de vértices debe estar entre 1 y 20. Ingrese nuevamente:");
			numVerticesIngresar = scanner.nextInt();
		}

		for (int i = 0; i < numVerticesIngresar; i++) {
			String nombreVertice = JOptionPane.showInputDialog("Ingrese el nombre del vértice " + (i + 1));
			nuevoVertice(nombreVertice);
		}

		int opcion = Integer.parseInt(JOptionPane.showInputDialog("¿Desea agregar más vértices? (Sí: 1, No: 0)"));

		while (opcion == 1) {
			String nombreVertice = JOptionPane.showInputDialog("Ingrese el nombre del vértice");
			nuevoVertice(nombreVertice);
			opcion = Integer.parseInt(JOptionPane.showInputDialog("¿Desea agregar más vértices? (Sí: 1, No: 0)"));
		}

		JOptionPane.showMessageDialog(null, "Ingrese la información de adyacencia entre los vértices:");
		for (int i = 0; i < numVerts; i++) {
			for (int j = 0; j < numVerts; j++) {
				if (i != j) {
					int esAdyacente = Integer.parseInt(JOptionPane.showInputDialog("¿El vértice " + verts[i].nombre + " es adyacente al vértice " + verts[j].nombre + "? (Sí: 1, No: 0)"));
					if (esAdyacente == 1) {
						try {
							nuevoArco(i, j);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null,"Error al agregar el arco: " + e.getMessage());
						}
					}
				}
			}
		}
	}

	public void mostrarInformacionGrafo() {
		JOptionPane.showMessageDialog(null, "Información del grafo:");

		// Mostrar lista de vértices
		for (int i = 0; i < numVerts; i++) {
			JOptionPane.showMessageDialog(null, "Vértice " + verts[i].nombre);
		}

		// Mostrar aristas adyacentes para cada vértice
		for (int i = 0; i < numVerts; i++) {
			JOptionPane.showMessageDialog(null, "Vértice " + verts[i].nombre + " es adyacente a: ");
			for (int j = 0; j < numVerts; j++) {
				try {
					if (adyacente(i, j)) {
						JOptionPane.showMessageDialog(null, verts[j].nombre + " ");
					}
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}
	}
}
