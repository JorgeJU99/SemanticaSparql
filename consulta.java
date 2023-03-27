import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileManager;

public class consulta extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel lblprogramacion = null;

	private JLabel lblcategoria = null;

	private JButton btnconsulta = null;

	private JComboBox jcbprogramacion = null;

	private JComboBox jcbcategoria = null;

	private JTextArea txtaconsulta = null;

	/**
	 * This method initializes btnconsulta
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnconsulta() {
		if (btnconsulta == null) {
			btnconsulta = new JButton();
			btnconsulta.setBounds(new Rectangle(447, 18, 54, 26));
			btnconsulta.setText("Ok");
			btnconsulta.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					/////////
					String prog = jcbprogramacion.getSelectedItem().toString();
					String cate = jcbcategoria.getSelectedItem().toString();
					FileManager.get().addLocatorClassLoader(consulta.class.getClassLoader());
					Model model = FileManager.get().loadModel("programa.rdf");

					String cadena = "prefix po: <http://purl.org/ontology/po/> "
							+ "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
							+ "select ?actor ?genero ?categoria " + "where { ?a po:Category ?c . "
							+ "?a po:Brand ?prog . " + "?a po:actor ?actor. " + "?a po:Genre ?genero ."
							+ "?a po:Category ?categoria." + "filter ((str(?c) = " + "'" + cate + "'" + ")"
							+ "&& (str(?prog) =" + "'" + prog + "'" + "))" + ". }";
					Query query = QueryFactory.create(cadena);
					QueryExecution qe = QueryExecutionFactory.create(query, model);
					try {
						ResultSet results = qe.execSelect();
						txtaconsulta.setText(ResultSetFormatter.asText(results));
					} finally {
						qe.close();
					}

					////////
				}
			});
		}
		return btnconsulta;
	}

	/**
	 * This method initializes jcbprogramacion
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJcbprogramacion() {
		if (jcbprogramacion == null) {
			jcbprogramacion = new JComboBox();
			jcbprogramacion.setBounds(new Rectangle(172, 19, 180, 31));
		}
		return jcbprogramacion;
	}

	/**
	 * This method initializes jcbcategoria
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJcbcategoria() {
		if (jcbcategoria == null) {
			jcbcategoria = new JComboBox();
			jcbcategoria.setBounds(new Rectangle(168, 65, 186, 29));
		}
		return jcbcategoria;
	}

	/**
	 * This method initializes txtaconsulta
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getTxtaconsulta() {
		if (txtaconsulta == null) {
			txtaconsulta = new JTextArea();
			txtaconsulta.setBounds(new Rectangle(54, 125, 380, 139));
		}
		return txtaconsulta;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				consulta thisClass = new consulta();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public consulta() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(533, 337);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		jcbprogramacion.addItem("12 anios de esclavitud");
		jcbprogramacion.addItem("El nacimiento de un imperio");
		jcbprogramacion.addItem("Aires de esperanza");
		jcbprogramacion.addItem("Batman Assault on Arkham");
		jcbprogramacion.addItem("Acomplejado");
		jcbprogramacion.addItem("Acumuladores");
		jcbprogramacion.addItem("Cuponmania");

		jcbcategoria.addItem("Peliculas");
		jcbcategoria.addItem("Entretenimiento");
		jcbcategoria.addItem("Farandula");

	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblcategoria = new JLabel();
			lblcategoria.setBounds(new Rectangle(30, 67, 123, 24));
			lblcategoria.setText("Categoria");
			lblprogramacion = new JLabel();
			lblprogramacion.setBounds(new Rectangle(27, 20, 129, 30));
			lblprogramacion.setText("Programacion");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lblprogramacion, null);
			jContentPane.add(lblcategoria, null);
			jContentPane.add(getBtnconsulta(), null);
			jContentPane.add(getJcbprogramacion(), null);
			jContentPane.add(getJcbcategoria(), null);
			jContentPane.add(getTxtaconsulta(), null);
		}
		return jContentPane;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
