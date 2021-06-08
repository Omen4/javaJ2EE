package com.mycompany.windowsform;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.mycompany.dao.DaoPlayerMysql;
import com.mycompany.metier.MetierTennis;
import com.mycompany.models.Epreuve;
import com.mycompany.models.Match;
import com.mycompany.models.Player;
import com.mycompany.models.Tournoi;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JInternalFrame;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;

import org.apache.tomcat.jni.File;

import javax.swing.border.BevelBorder;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTable;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TennisAppForm {
	private MetierTennis metierTennis = new MetierTennis();
	private JFrame frmTennisapp;
	private String[] sexe = {"H", "F"};
	private List<String> optTournois = metierTennis.getTournois();
	private List<Integer> optAnnees = metierTennis.getAnnees();
	
	private JTable tableJoueurs;
	private String[] columnNamesPlayers = {"id","nom","prenom","sexe"}; 
	private List<Player> joueurs = metierTennis.listerJoueurs();
	private Player selectedJoueur;
	private JTextField textFieldFiltreNom;
	private JTextField textFieldModifierNom;
	private JTextField textFieldModifierPrenom;
	private JComboBox comboBoxModifierSexe;
	private JComboBox comboBoxFiltreSexe;
	private JLabel logoLabel;
	private ImageIcon image = new ImageIcon("C:\\Users\\Solk\\Pictures\\nicetennislogo.png");
	
	private JTable tableTournoi;
	private String[] columnNamesTournois = {"id","tournoi","code"};
	private List<Tournoi> tournois = metierTennis.listerTournois();
	private Tournoi selectedTournoi;
	private JTextField textFieldFiltreNom_Tournoi;
	private JTextField textFieldFiltreCode_Tournoi;
	private JTextField textFieldModifierNom_Tournoi;
	private JTextField textFieldModifierCode_Tournoi;
	private JLabel logoLabel_Tournoi;
	private ImageIcon image_Tournoi = new ImageIcon("C:\\Users\\Solk\\Pictures\\nicetennislogo.png");
	
	private JTable tableMatch;
	private String[] columnNamesMatchs = {"id","vainqueur","","finaliste","","set1","set2","set3","set4","set5"};
	private List<Match> matchs = metierTennis.listerMatchs();
//	private Match selectedMatch;
	private JTextField textFieldFiltreNom_Match;
	private JComboBox comboBoxFiltreAnnee_Match;
	private JComboBox<String> comboBoxFiltreTournoi_Match;
	private JComboBox comboBoxFiltreSexe_Match;
	private JLabel logoLabel_Match;
	private ImageIcon image_Match = new ImageIcon("C:\\Users\\Solk\\Pictures\\nicetennislogo.png");

	
	private JTable tableEpreuve;
	private String[] columnNamesEpreuves = {"id","annee","type","tournoi"};
	private List<Epreuve> epreuves = metierTennis.listerEpreuves();
//	private Epreuve selectedEpreuve;
	private JTextField textFieldFiltreNom_Epreuve;
	private JComboBox comboBoxFiltreAnnee_Epreuve;
	private JComboBox<String> comboBoxFiltreTournoi_Epreuve;
	private JComboBox comboBoxFiltreSexe_Epreuve;
	private JLabel logoLabel_Epreuve;
	private ImageIcon image_Epreuve = new ImageIcon("C:\\Users\\Solk\\Pictures\\nicetennislogo.png");


	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String win2 = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
					UIManager.setLookAndFeel(win2);
					TennisAppForm window = new TennisAppForm();
					window.frmTennisapp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TennisAppForm() {
		this.initialize();
		this.refreshTablePlayer();
		this.refreshTableTournoi();
		this.refreshTableMatchs();
		this.refreshTableEpreuves();
	}
	
//TODO METTRE LES THIS!
	public void refreshTablePlayer()
    {	 
        String[][] dataPlayer = new String[this.joueurs.size()][columnNamesPlayers.length];
        for (int i=0; i<this.joueurs.size(); i++) {
        	dataPlayer[i] = this.joueurs.get(i).toArray();
        }
        this.tableJoueurs.setModel(new DefaultTableModel(dataPlayer, columnNamesPlayers) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        });
    }
	
	//TOURNOI
	public void refreshTableTournoi()
    {	
        String[][] dataTournament = new String[this.tournois.size()][columnNamesTournois.length];
        for (int i=0; i<this.tournois.size(); i++) {
        	dataTournament[i] = this.tournois.get(i).toArray();
        }
        this.tableTournoi.setModel(new DefaultTableModel(dataTournament, columnNamesTournois) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        });
    }
	
	//MATCHS
	public void refreshTableMatchs()
    {	
        String[][] dataMatchs = new String[this.matchs.size()][columnNamesMatchs.length];
        
        for (int i=0; i<this.matchs.size(); i++) {
        	dataMatchs[i] = this.matchs.get(i).toArray();
        }
       
        	
        this.tableMatch.setModel(new DefaultTableModel(dataMatchs, columnNamesMatchs) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        });
    }
		
	//EPREUVES
	public void refreshTableEpreuves()

    {	
        String[][] dataEpreuves = new String[this.epreuves.size()][columnNamesEpreuves.length];
        for (int i=0; i<this.epreuves.size(); i++) {
        	dataEpreuves[i] = this.epreuves.get(i).toArray();
        }
        this.tableEpreuve.setModel(new DefaultTableModel(dataEpreuves, columnNamesEpreuves) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        });
    }

	
	private void initialize(){
		frmTennisapp = new JFrame();
		frmTennisapp.getContentPane().setBackground(Color.WHITE);
		frmTennisapp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frmTennisapp.setResizable(false);
		frmTennisapp.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Solk\\Pictures\\tennis-raquet-and-ball.png"));
		frmTennisapp.setFont(new Font("Lucida Console", Font.PLAIN, 12));
		frmTennisapp.getContentPane().setFont(new Font("Lucida Console", Font.PLAIN, 11));
		frmTennisapp.setTitle("HVYTennisAppForNadia");
		frmTennisapp.setBounds(100, 100, 999, 666);
		frmTennisapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setForeground(Color.MAGENTA);
		tabbedPane.setMinimumSize(new Dimension(10, 10));
		tabbedPane.setAlignmentY(1.0f);
		tabbedPane.setAlignmentX(1.0f);
		tabbedPane.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		frmTennisapp.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		System.out.println(System.getProperty("user.dir"));
		
//JOUEUR/////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////	
		JPanel joueursPanel = new JPanel();
		joueursPanel.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		joueursPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		tabbedPane.addTab("Joueurs", null, joueursPanel, null);
		tabbedPane.setForegroundAt(0, Color.MAGENTA);
		GridBagLayout gbl_joueursPanel = new GridBagLayout();
		gbl_joueursPanel.columnWidths = new int[]{525, 0};
		gbl_joueursPanel.rowHeights = new int[]{85, 90, 178, 0};
		gbl_joueursPanel.columnWeights = new double[]{1.0, 1.0};
		gbl_joueursPanel.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		joueursPanel.setLayout(gbl_joueursPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		joueursPanel.add(scrollPane, gbc_scrollPane);
		
//SELECTION
		tableJoueurs = new JTable();
		tableJoueurs.addMouseListener(new MouseAdapter() {
			@Override
	        public void mousePressed(MouseEvent e) {
				selectedJoueur = joueurs.get(tableJoueurs.getSelectedRow());
	            textFieldModifierNom.setText(selectedJoueur.getNom());
	            textFieldModifierPrenom.setText(selectedJoueur.getPrenom());
	            comboBoxModifierSexe.setSelectedItem(selectedJoueur.isMale() ? "H" : "F");
		     }
		});
		tableJoueurs.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(tableJoueurs);
		
		JPanel logoPanel = new JPanel();
		logoPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		logoPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_logoPanel = new GridBagConstraints();
		gbc_logoPanel.insets = new Insets(0, 0, 5, 0);
		gbc_logoPanel.fill = GridBagConstraints.BOTH;
		gbc_logoPanel.gridx = 1;
		gbc_logoPanel.gridy = 0;
		joueursPanel.add(logoPanel, gbc_logoPanel);
		logoPanel.setLayout(new GridLayout(0, 1, 0, 0));
		logoLabel = new JLabel(image);
		logoLabel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		logoPanel.add(logoLabel);
	
		
		JPanel searchFieldPanel = new JPanel();
		GridBagConstraints gbc_searchFieldPanel = new GridBagConstraints();
		gbc_searchFieldPanel.insets = new Insets(0, 0, 5, 0);
		gbc_searchFieldPanel.fill = GridBagConstraints.BOTH;
		gbc_searchFieldPanel.gridx = 1;
		gbc_searchFieldPanel.gridy = 1;
		joueursPanel.add(searchFieldPanel, gbc_searchFieldPanel);
		searchFieldPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblFiltreFiltrerPar = new JLabel("FILTRER PAR:");
		lblFiltreFiltrerPar.setForeground(Color.MAGENTA);
		lblFiltreFiltrerPar.setHorizontalAlignment(SwingConstants.LEFT);
		lblFiltreFiltrerPar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFiltreFiltrerPar.setBackground(Color.YELLOW);
		searchFieldPanel.add(lblFiltreFiltrerPar);
		
//FILTRAGE DE LA RECHERCHE
		JButton btnFiltrerPar = new JButton("GO!");
		btnFiltrerPar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(comboBoxFiltreSexe.getSelectedItem() != null) {
					joueurs = joueurs.stream()
					     .filter(item -> item.getNom().toLowerCase().contains(textFieldFiltreNom.getText().toLowerCase()) 
					    		 || item.getPrenom().toLowerCase().contains(textFieldFiltreNom.getText().toLowerCase()))	
					     .filter(item ->item.isMale()==(comboBoxFiltreSexe.getSelectedItem().equals("H")))
					     .collect(Collectors.toList());
				}else {
					joueurs = joueurs.stream()
					     .filter(item -> item.getNom().toLowerCase().contains(textFieldFiltreNom.getText().toLowerCase()) 
					    		 || item.getPrenom().toLowerCase().contains(textFieldFiltreNom.getText().toLowerCase()))
					     .collect(Collectors.toList());
				}
				refreshTablePlayer();
			}
		});
		btnFiltrerPar.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		btnFiltrerPar.setForeground(Color.MAGENTA);
		btnFiltrerPar.setBackground(Color.YELLOW);
		searchFieldPanel.add(btnFiltrerPar);
		
		JLabel lblFiltreNom = new JLabel("NOM/PRENOM: ");
		lblFiltreNom.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblFiltreNom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFiltreNom.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblFiltreNom.setForeground(Color.BLACK);
		lblFiltreNom.setBackground(Color.WHITE);
		lblFiltreNom.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		searchFieldPanel.add(lblFiltreNom);
		
		textFieldFiltreNom = new JTextField("");
		textFieldFiltreNom.setColumns(10);
		textFieldFiltreNom.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		searchFieldPanel.add(textFieldFiltreNom);
		
		JLabel lblFiltreSexe = new JLabel("SEXE: ");
		lblFiltreSexe.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblFiltreSexe.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFiltreSexe.setForeground(Color.BLACK);
		lblFiltreSexe.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFiltreSexe.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblFiltreSexe.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		searchFieldPanel.add(lblFiltreSexe);
		
		comboBoxFiltreSexe = new JComboBox(sexe);
		comboBoxFiltreSexe.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		comboBoxFiltreSexe.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		comboBoxFiltreSexe.setSelectedIndex(-1);
		searchFieldPanel.add(comboBoxFiltreSexe);
		
		JPanel modifyPanel = new JPanel();
		GridBagConstraints gbc_modifyPanel = new GridBagConstraints();
		gbc_modifyPanel.fill = GridBagConstraints.BOTH;
		gbc_modifyPanel.gridx = 1;
		gbc_modifyPanel.gridy = 2;
		joueursPanel.add(modifyPanel, gbc_modifyPanel);
		modifyPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
//CHAMP DE MODIFICATION
		JLabel lblModifierTitre = new JLabel("MODIFIER:");
		lblModifierTitre.setForeground(Color.MAGENTA);
		lblModifierTitre.setHorizontalAlignment(SwingConstants.LEFT);
		lblModifierTitre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblModifierTitre.setBackground(Color.YELLOW);
		modifyPanel.add(lblModifierTitre);
		
		JLabel lblModifierTitreBlank = new JLabel("");
		modifyPanel.add(lblModifierTitreBlank);
		
		JLabel lblModifierNom = new JLabel("NOM: ");
		lblModifierNom.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblModifierNom.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblModifierNom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModifierNom.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		modifyPanel.add(lblModifierNom);
		
		textFieldModifierNom = new JTextField();
		modifyPanel.add(textFieldModifierNom);
		textFieldModifierNom.setColumns(10);
		
		
		JLabel lblModifierPrenom = new JLabel("PRENOM: ");
		lblModifierPrenom.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblModifierPrenom.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblModifierPrenom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModifierPrenom.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		modifyPanel.add(lblModifierPrenom);
		
		textFieldModifierPrenom = new JTextField();
		textFieldModifierPrenom.setColumns(10);
		textFieldModifierPrenom.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		modifyPanel.add(textFieldModifierPrenom);
		
		JLabel lblModifierSexe = new JLabel("SEXE: ");
		lblModifierSexe.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblModifierSexe.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblModifierSexe.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModifierSexe.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		modifyPanel.add(lblModifierSexe);
		
		comboBoxModifierSexe = new JComboBox(sexe);
		comboBoxModifierSexe.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		comboBoxModifierSexe.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		comboBoxModifierSexe.setSelectedIndex(-1);
		modifyPanel.add(comboBoxModifierSexe);

//CHAMP D'ACTIONS BOUTONS	
		JButton btnModifierAjouter = new JButton("Ajouter");
		btnModifierAjouter.setForeground(Color.MAGENTA);
		btnModifierAjouter.setBackground(Color.YELLOW);
		btnModifierAjouter.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		btnModifierAjouter.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnModifierAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player tempJoueur = new Player();
				tempJoueur.setNom(textFieldModifierNom.getText());
				tempJoueur.setPrenom(textFieldModifierPrenom.getText());
				tempJoueur.setMale(comboBoxModifierSexe.getSelectedItem().equals("H"));
				metierTennis.ajouterJoueur(tempJoueur);
				joueurs = metierTennis.listerJoueurs();
				refreshTablePlayer();
			}
		});
		modifyPanel.add(btnModifierAjouter);

		JButton btnModifierModifier = new JButton("Modifier");
		btnModifierModifier.setForeground(Color.MAGENTA);
		btnModifierModifier.setBackground(Color.YELLOW);
		btnModifierModifier.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		btnModifierModifier.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnModifierModifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedJoueur.setNom(textFieldModifierNom.getText());
				selectedJoueur.setPrenom(textFieldModifierPrenom.getText());
				selectedJoueur.setMale(comboBoxModifierSexe.getSelectedItem().equals("H"));
				metierTennis.modifierJoueur(selectedJoueur);
				joueurs = metierTennis.listerJoueurs();
				refreshTablePlayer();
			}
		});
		modifyPanel.add(btnModifierModifier);

		JButton btnModifierSupprimer = new JButton("Supprimer");
		btnModifierSupprimer.setForeground(Color.MAGENTA);
		btnModifierSupprimer.setBackground(Color.YELLOW);
		btnModifierSupprimer.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		btnModifierSupprimer.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnModifierSupprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				metierTennis.supprimerJoueur(selectedJoueur);
				joueurs = metierTennis.listerJoueurs();
				refreshTablePlayer();
			}
		});
		modifyPanel.add(btnModifierSupprimer);

		
		JButton btnModifierReset = new JButton("Reset");
		btnModifierReset.setForeground(Color.MAGENTA);
		btnModifierReset.setBackground(Color.YELLOW);
		btnModifierReset.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		btnModifierReset.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnModifierReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldFiltreNom.setText(null);
				textFieldModifierNom.setText(null);
				textFieldModifierPrenom.setText(null);
				comboBoxModifierSexe.setSelectedItem(null);
				comboBoxFiltreSexe.setSelectedItem(null);
				joueurs = metierTennis.listerJoueurs();
				refreshTablePlayer();
				System.out.println("resetPlayer");
			}
		});
		modifyPanel.add(btnModifierReset);
	
		
//TOURNOI///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////	
//////////////////////////////////////////////////////////////////////////////////////

	JPanel tournoiPanel = new JPanel();
	tournoiPanel.setFont(new Font("Lucida Console", Font.PLAIN, 11));
	tournoiPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	tabbedPane.addTab("Tournois", null, tournoiPanel, null);
	tabbedPane.setForegroundAt(0, Color.MAGENTA);
	GridBagLayout gbl_tournoiPanel = new GridBagLayout();
	gbl_tournoiPanel.columnWidths = new int[]{479, 0};
	gbl_tournoiPanel.rowHeights = new int[]{85, 90, 178, 0};
	gbl_tournoiPanel.columnWeights = new double[]{1.0, 1.0};
	gbl_tournoiPanel.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
	tournoiPanel.setLayout(gbl_tournoiPanel);
	
	JScrollPane tournoiScrollPane = new JScrollPane();
	tournoiScrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
	GridBagConstraints gbc_tournoiScrollPane = new GridBagConstraints();
	gbc_tournoiScrollPane.gridheight = 3;
	gbc_tournoiScrollPane.insets = new Insets(0, 0, 0, 5);
	gbc_tournoiScrollPane.fill = GridBagConstraints.BOTH;
	gbc_tournoiScrollPane.gridx = 0;
	gbc_tournoiScrollPane.gridy = 0;
	tournoiPanel.add(tournoiScrollPane, gbc_tournoiScrollPane);
	
//SELECTION
	tableTournoi = new JTable();
	tableTournoi.addMouseListener(new MouseAdapter() {
		@Override
        public void mousePressed(MouseEvent e) {
			selectedTournoi = tournois.get(tableTournoi.getSelectedRow());
            textFieldModifierNom_Tournoi.setText(selectedTournoi.getNomTournoi());
            textFieldModifierCode_Tournoi.setText(selectedTournoi.getCodeTournoi());
	     }
	});
	tableTournoi.setBackground(Color.LIGHT_GRAY);
	tournoiScrollPane.setViewportView(tableTournoi);
	
	JPanel logoPanel_Tournoi = new JPanel();
	logoPanel_Tournoi.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	logoPanel_Tournoi.setBackground(Color.WHITE);
	GridBagConstraints gbc_logoPanel_Tournoi = new GridBagConstraints();
	gbc_logoPanel_Tournoi.insets = new Insets(0, 0, 5, 0);
	gbc_logoPanel_Tournoi.fill = GridBagConstraints.BOTH;
	gbc_logoPanel_Tournoi.gridx = 1;
	gbc_logoPanel_Tournoi.gridy = 0;
	tournoiPanel.add(logoPanel_Tournoi, gbc_logoPanel_Tournoi);
	logoPanel_Tournoi.setLayout(new GridLayout(0, 1, 0, 0)); 
	logoLabel_Tournoi = new JLabel(image);
	logoLabel_Tournoi.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	logoPanel_Tournoi.add(logoLabel_Tournoi);
	
	JPanel searchFieldPanel_Tournoi = new JPanel();
	GridBagConstraints gbc_searchFieldPanel_Tournoi = new GridBagConstraints();
	gbc_searchFieldPanel_Tournoi.insets = new Insets(0, 0, 5, 0);
	gbc_searchFieldPanel_Tournoi.fill = GridBagConstraints.BOTH;
	gbc_searchFieldPanel_Tournoi.gridx = 1;
	gbc_searchFieldPanel_Tournoi.gridy = 1;
	tournoiPanel.add(searchFieldPanel_Tournoi, gbc_searchFieldPanel_Tournoi);
	searchFieldPanel_Tournoi.setLayout(new GridLayout(0, 2, 0, 0));
	
	JLabel lblFiltreFiltrerParTournoi = new JLabel("FILTRER PAR:");
	lblFiltreFiltrerParTournoi.setForeground(Color.MAGENTA);
	lblFiltreFiltrerParTournoi.setHorizontalAlignment(SwingConstants.LEFT);
	lblFiltreFiltrerParTournoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblFiltreFiltrerParTournoi.setBackground(Color.YELLOW);
	searchFieldPanel_Tournoi.add(lblFiltreFiltrerParTournoi);
	
//FILTRAGE DE LA RECHERCHE
	JButton btnFiltrerPar_Tournoi = new JButton("GO!");
	btnFiltrerPar_Tournoi.setFont(new Font("Lucida Console", Font.PLAIN, 11));
	btnFiltrerPar_Tournoi.setForeground(Color.MAGENTA);
	btnFiltrerPar_Tournoi.setBackground(Color.YELLOW);
	btnFiltrerPar_Tournoi.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			tournois = tournois.stream()
			     .filter(item -> item.getNomTournoi().toLowerCase().contains(textFieldFiltreNom_Tournoi.getText().toLowerCase()))	
			     .filter(item -> item.getCodeTournoi().toLowerCase().contains(textFieldFiltreCode_Tournoi.getText().toLowerCase()))
			     .collect(Collectors.toList());
			refreshTableTournoi();
		}
	});
	searchFieldPanel_Tournoi.add(btnFiltrerPar_Tournoi);
	
	JLabel lblFiltreNom_Tournoi = new JLabel("NOM: ");
	lblFiltreNom_Tournoi.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	lblFiltreNom_Tournoi.setHorizontalAlignment(SwingConstants.RIGHT);
	lblFiltreNom_Tournoi.setVerticalTextPosition(SwingConstants.BOTTOM);
	lblFiltreNom_Tournoi.setForeground(Color.BLACK);
	lblFiltreNom_Tournoi.setBackground(Color.WHITE);
	lblFiltreNom_Tournoi.setFont(new Font("Lucida Console", Font.PLAIN, 14));
	searchFieldPanel_Tournoi.add(lblFiltreNom_Tournoi);
	
	textFieldFiltreNom_Tournoi = new JTextField("");
	textFieldFiltreNom_Tournoi.setColumns(10);
	textFieldFiltreNom_Tournoi.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
	searchFieldPanel_Tournoi.add(textFieldFiltreNom_Tournoi);
	
	
	JLabel lblFiltreCode_Tournoi = new JLabel("CODE: ");
	lblFiltreCode_Tournoi .setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	lblFiltreCode_Tournoi .setHorizontalAlignment(SwingConstants.RIGHT);
	lblFiltreCode_Tournoi .setVerticalTextPosition(SwingConstants.BOTTOM);
	lblFiltreCode_Tournoi .setForeground(Color.BLACK);
	lblFiltreCode_Tournoi .setBackground(Color.WHITE);
	lblFiltreCode_Tournoi .setFont(new Font("Lucida Console", Font.PLAIN, 14));
	searchFieldPanel_Tournoi.add(lblFiltreCode_Tournoi );
	
	textFieldFiltreCode_Tournoi = new JTextField("");
	textFieldFiltreCode_Tournoi.setColumns(10);
	textFieldFiltreCode_Tournoi.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
	searchFieldPanel_Tournoi.add(textFieldFiltreCode_Tournoi);

//CHAMP DE MODIFICATION
	JPanel modifyPanel_Tournoi = new JPanel();
	GridBagConstraints gbc_modifyPanel_Tournoi = new GridBagConstraints();
	gbc_modifyPanel_Tournoi.fill = GridBagConstraints.BOTH;
	gbc_modifyPanel_Tournoi.gridx = 1;
	gbc_modifyPanel_Tournoi.gridy = 2;
	tournoiPanel.add(modifyPanel_Tournoi, gbc_modifyPanel_Tournoi);
	modifyPanel_Tournoi.setLayout(new GridLayout(0, 2, 0, 0));
	
	JLabel lblModifierTitre_Tournoi = new JLabel("MODIFIER:");
	lblModifierTitre_Tournoi.setForeground(Color.MAGENTA);
	lblModifierTitre_Tournoi.setHorizontalAlignment(SwingConstants.LEFT);
	lblModifierTitre_Tournoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblModifierTitre_Tournoi.setBackground(Color.YELLOW);
	modifyPanel_Tournoi.add(lblModifierTitre_Tournoi);
	
	JLabel lblModifierTitreBlank_Tournoi = new JLabel("");
	modifyPanel_Tournoi.add(lblModifierTitreBlank_Tournoi);
	
	JLabel lblModifierNom_Tournoi = new JLabel("NOM: ");
	lblModifierNom_Tournoi.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	lblModifierNom_Tournoi.setVerticalTextPosition(SwingConstants.BOTTOM);
	lblModifierNom_Tournoi.setHorizontalAlignment(SwingConstants.RIGHT);
	lblModifierNom_Tournoi.setFont(new Font("Lucida Console", Font.PLAIN, 14));
	modifyPanel_Tournoi.add(lblModifierNom_Tournoi);
	
	textFieldModifierNom_Tournoi = new JTextField();
	modifyPanel_Tournoi.add(textFieldModifierNom_Tournoi);
	textFieldModifierNom_Tournoi.setColumns(10);
	
	
	JLabel lblModifierCode_Tournoi = new JLabel("CODE: ");
	lblModifierCode_Tournoi.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	lblModifierCode_Tournoi.setVerticalTextPosition(SwingConstants.BOTTOM);
	lblModifierCode_Tournoi.setHorizontalAlignment(SwingConstants.RIGHT);
	lblModifierCode_Tournoi.setFont(new Font("Lucida Console", Font.PLAIN, 14));
	modifyPanel_Tournoi.add(lblModifierCode_Tournoi);
	
	textFieldModifierCode_Tournoi = new JTextField();
	textFieldModifierCode_Tournoi.setColumns(10);
	textFieldModifierCode_Tournoi.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
	modifyPanel_Tournoi.add(textFieldModifierCode_Tournoi);

//CHAMP D'ACTIONS BOUTONS	
	JButton btnModifierAjouter_Tournoi = new JButton("Ajouter");
	btnModifierAjouter_Tournoi.setForeground(Color.MAGENTA);
	btnModifierAjouter_Tournoi.setBackground(Color.YELLOW);
	btnModifierAjouter_Tournoi.setFont(new Font("Lucida Console", Font.PLAIN, 11));
	btnModifierAjouter_Tournoi.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	btnModifierAjouter_Tournoi.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Tournoi tempTournoi = new Tournoi();
			tempTournoi.setNomTournoi(textFieldModifierNom_Tournoi.getText());
			tempTournoi.setCodeTournoi(textFieldModifierCode_Tournoi.getText());
			metierTennis.ajouterTournoi(tempTournoi);
			tournois = metierTennis.listerTournois();
			refreshTableTournoi();
		}
	});
	modifyPanel_Tournoi.add(btnModifierAjouter_Tournoi);
	
	JButton btnModifierModifier_Tournoi = new JButton("Modifier");
	btnModifierModifier_Tournoi.setForeground(Color.MAGENTA);
	btnModifierModifier_Tournoi.setBackground(Color.YELLOW);
	btnModifierModifier_Tournoi.setFont(new Font("Lucida Console", Font.PLAIN, 11));
	btnModifierModifier_Tournoi.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	btnModifierModifier_Tournoi.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			selectedTournoi.setNomTournoi(textFieldModifierNom_Tournoi.getText());
			selectedTournoi.setCodeTournoi(textFieldModifierCode_Tournoi.getText());
			metierTennis.modifierTournoi(selectedTournoi);
			tournois = metierTennis.listerTournois();
			refreshTableTournoi();
		}
	});
	modifyPanel_Tournoi.add(btnModifierModifier_Tournoi);

	JButton btnModifierSupprimer_Tournoi = new JButton("Supprimer");
	btnModifierSupprimer_Tournoi.setForeground(Color.MAGENTA);
	btnModifierSupprimer_Tournoi.setBackground(Color.YELLOW);
	btnModifierSupprimer_Tournoi.setFont(new Font("Lucida Console", Font.PLAIN, 11));
	btnModifierSupprimer_Tournoi.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	btnModifierSupprimer_Tournoi.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			metierTennis.supprimerTournoi(selectedTournoi);
			tournois = metierTennis.listerTournois();
			refreshTableTournoi();
		}
	});
	modifyPanel_Tournoi.add(btnModifierSupprimer_Tournoi);
	
	JButton btnModifierReset_Tournoi = new JButton("Reset");
	btnModifierReset_Tournoi.setForeground(Color.MAGENTA);
	btnModifierReset_Tournoi.setBackground(Color.YELLOW);
	btnModifierReset_Tournoi.setFont(new Font("Lucida Console", Font.PLAIN, 11));
	btnModifierReset_Tournoi.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	btnModifierReset_Tournoi.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			textFieldFiltreNom_Tournoi.setText(null);
			textFieldFiltreCode_Tournoi.setText(null);
			textFieldModifierNom_Tournoi.setText(null);
			textFieldModifierCode_Tournoi.setText(null);
			tournois = metierTennis.listerTournois();
			refreshTableTournoi();
			System.out.println("resetTournois");
		}
	});
	modifyPanel_Tournoi.add(btnModifierReset_Tournoi);

		
//MATCH/////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////	
//////////////////////////////////////////////////////////////////////////////////////

	JPanel matchPanel = new JPanel();
	matchPanel.setFont(new Font("Lucida Console", Font.PLAIN, 11));
	matchPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	tabbedPane.addTab("Match", null, matchPanel, null);
	tabbedPane.setForegroundAt(0, Color.MAGENTA);
	GridBagLayout gbl_matchPanel = new GridBagLayout();
	gbl_matchPanel.columnWidths = new int[]{700, 0};
	gbl_matchPanel.rowHeights = new int[]{85, 90, 178, 0};
	gbl_matchPanel.columnWeights = new double[]{1.0, 1.0};
	gbl_matchPanel.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
	matchPanel.setLayout(gbl_matchPanel);
	
	JScrollPane matchScrollPane = new JScrollPane();
	matchScrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
	GridBagConstraints gbc_matchScrollPane = new GridBagConstraints();
	gbc_matchScrollPane.gridheight = 3;
	gbc_matchScrollPane.insets = new Insets(0, 0, 0, 5);
	gbc_matchScrollPane.fill = GridBagConstraints.BOTH;
	gbc_matchScrollPane.gridx = 0;
	gbc_matchScrollPane.gridy = 0;
	matchPanel.add(matchScrollPane, gbc_matchScrollPane);
	
//SELECTION
	tableMatch = new JTable();
	tableMatch.addMouseListener(new MouseAdapter() {
		@Override
        public void mousePressed(MouseEvent e) {
//TODO Envoyer la selection vers les champs de modif
//			selectedMatch = matchs.get(tableMatch.getSelectedRow());
//          textFieldModifierNom_Match.setText(selectedMatch.getNomMatch());
//          textFieldModifierNom_Match.setText(selectedMatch.getCodeMatch());
//          textFieldModifierNom_Match.setText(selectedMatch.getCodeMatch());
	     }
	});
	tableMatch.setBackground(Color.LIGHT_GRAY);
	matchScrollPane.setViewportView(tableMatch);
	
	JPanel logoPanel_Match = new JPanel();
	logoPanel_Match.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	logoPanel_Match.setBackground(Color.WHITE);
	GridBagConstraints gbc_logoPanel_Match = new GridBagConstraints();
	gbc_logoPanel_Match.insets = new Insets(0, 0, 5, 0);
	gbc_logoPanel_Match.fill = GridBagConstraints.BOTH;
	gbc_logoPanel_Match.gridx = 1;
	gbc_logoPanel_Match.gridy = 0;
	matchPanel.add(logoPanel_Match, gbc_logoPanel_Match);
	logoPanel_Match.setLayout(new GridLayout(0, 1, 0, 0)); 
	logoLabel_Match = new JLabel(image_Match);
	logoLabel_Match.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	logoPanel_Match.add(logoLabel_Match);

	
	JPanel searchFieldPanel_Match = new JPanel();
	GridBagConstraints gbc_searchFieldPanel_Match = new GridBagConstraints();
	gbc_searchFieldPanel_Match.insets = new Insets(0, 0, 5, 0);
	gbc_searchFieldPanel_Match.fill = GridBagConstraints.BOTH;
	gbc_searchFieldPanel_Match.gridx = 1;
	gbc_searchFieldPanel_Match.gridy = 1;
	matchPanel.add(searchFieldPanel_Match, gbc_searchFieldPanel_Match);
	searchFieldPanel_Match.setLayout(new GridLayout(0, 2, 0, 0));
	
	JLabel lblFiltreFiltrerPar_Match = new JLabel("FILTRER PAR:");
	lblFiltreFiltrerPar_Match.setForeground(Color.MAGENTA);
	lblFiltreFiltrerPar_Match.setHorizontalAlignment(SwingConstants.LEFT);
	lblFiltreFiltrerPar_Match.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblFiltreFiltrerPar_Match.setBackground(Color.YELLOW);
	searchFieldPanel_Match.add(lblFiltreFiltrerPar_Match);
	
//FILTRAGE DE LA RECHERCHE
	JButton btnFiltrerPar_Match = new JButton("GO!");
	btnFiltrerPar_Match.setFont(new Font("Lucida Console", Font.PLAIN, 11));
	btnFiltrerPar_Match.setForeground(Color.MAGENTA);
	btnFiltrerPar_Match.setBackground(Color.YELLOW);
	btnFiltrerPar_Match.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			matchs = matchs.stream()
			     .filter(item -> (item.getPlayer1().getNom().toLowerCase().contains(textFieldFiltreNom_Match.getText().toLowerCase())
			    		 || item.getPlayer1().getPrenom().toLowerCase().contains(textFieldFiltreNom_Match.getText().toLowerCase())
			    		 || item.getPlayer2().getNom().toLowerCase().contains(textFieldFiltreNom_Match.getText().toLowerCase())
			    		 || item.getPlayer2().getPrenom().toLowerCase().contains(textFieldFiltreNom_Match.getText().toLowerCase()))
			    		 	&&((comboBoxFiltreSexe_Match.getSelectedItem() == null) || (item.getPlayer1().isMale() == comboBoxFiltreSexe_Match.getSelectedItem().equals("H")))
			    		 	&&((comboBoxFiltreTournoi_Match.getSelectedItem() == null) || (item.getEpreuve().getTournoi().getNomTournoi().equals(comboBoxFiltreTournoi_Match.getSelectedItem())))
			    		 	&&((comboBoxFiltreAnnee_Match.getSelectedItem() == null) || (item.getEpreuve().getAnneeEpreuve()==((int)comboBoxFiltreAnnee_Match.getSelectedItem())))
			    		 )
			     .collect(Collectors.toList());
			refreshTableMatchs();
			System.out.println("recherche Match");
		}
	});
	searchFieldPanel_Match.add(btnFiltrerPar_Match);
	
	JLabel lblFiltreNom_Match = new JLabel("NOM/PRENOM: ");
	lblFiltreNom_Match.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	lblFiltreNom_Match.setHorizontalAlignment(SwingConstants.RIGHT);
	lblFiltreNom_Match.setVerticalTextPosition(SwingConstants.BOTTOM);
	lblFiltreNom_Match.setForeground(Color.BLACK);
	lblFiltreNom_Match.setBackground(Color.WHITE);
	lblFiltreNom_Match.setFont(new Font("Lucida Console", Font.PLAIN, 14));
	searchFieldPanel_Match.add(lblFiltreNom_Match);
	
	textFieldFiltreNom_Match = new JTextField("");
	textFieldFiltreNom_Match.setColumns(10);
	textFieldFiltreNom_Match.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
	searchFieldPanel_Match.add(textFieldFiltreNom_Match);
	
	JLabel lblFiltreSexe_Match = new JLabel("SEXE: ");
	lblFiltreSexe_Match.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	lblFiltreSexe_Match.setHorizontalTextPosition(SwingConstants.CENTER);
	lblFiltreSexe_Match.setForeground(Color.BLACK);
	lblFiltreSexe_Match.setHorizontalAlignment(SwingConstants.RIGHT);
	lblFiltreSexe_Match.setVerticalTextPosition(SwingConstants.BOTTOM);
	lblFiltreSexe_Match.setFont(new Font("Lucida Console", Font.PLAIN, 14));
	searchFieldPanel_Match.add(lblFiltreSexe_Match);
	
	comboBoxFiltreSexe_Match = new JComboBox(sexe);
	comboBoxFiltreSexe_Match.setFont(new Font("Lucida Console", Font.PLAIN, 11));
	comboBoxFiltreSexe_Match.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
	comboBoxFiltreSexe_Match.setSelectedIndex(-1);
	searchFieldPanel_Match.add(comboBoxFiltreSexe_Match);
	
	JLabel lblFiltreTournoi_Match = new JLabel("TOURNOI: ");
	lblFiltreTournoi_Match.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	lblFiltreTournoi_Match.setHorizontalTextPosition(SwingConstants.CENTER);
	lblFiltreTournoi_Match.setForeground(Color.BLACK);
	lblFiltreTournoi_Match.setHorizontalAlignment(SwingConstants.RIGHT);
	lblFiltreTournoi_Match.setVerticalTextPosition(SwingConstants.BOTTOM);
	lblFiltreTournoi_Match.setFont(new Font("Lucida Console", Font.PLAIN, 14));
	searchFieldPanel_Match.add(lblFiltreTournoi_Match);
	
	comboBoxFiltreTournoi_Match = new JComboBox(optTournois.toArray());
	comboBoxFiltreTournoi_Match.setFont(new Font("Lucida Console", Font.PLAIN, 11));
	comboBoxFiltreTournoi_Match.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
	comboBoxFiltreTournoi_Match.setSelectedIndex(-1);
	searchFieldPanel_Match.add(comboBoxFiltreTournoi_Match);
	
	JLabel lblFiltreAnnee_Match = new JLabel("ANNÉE: ");
	lblFiltreAnnee_Match.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	lblFiltreAnnee_Match.setHorizontalTextPosition(SwingConstants.CENTER);
	lblFiltreAnnee_Match.setForeground(Color.BLACK);
	lblFiltreAnnee_Match.setHorizontalAlignment(SwingConstants.RIGHT);
	lblFiltreAnnee_Match.setVerticalTextPosition(SwingConstants.BOTTOM);
	lblFiltreAnnee_Match.setFont(new Font("Lucida Console", Font.PLAIN, 14));
	searchFieldPanel_Match.add(lblFiltreAnnee_Match);

	comboBoxFiltreAnnee_Match = new JComboBox(optAnnees.toArray());
	comboBoxFiltreAnnee_Match.setFont(new Font("Lucida Console", Font.PLAIN, 11));
	comboBoxFiltreAnnee_Match.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
	comboBoxFiltreAnnee_Match.setSelectedIndex(-1);
	searchFieldPanel_Match.add(comboBoxFiltreAnnee_Match);

	
//CHAMP DE MODIFICATION	


//CHAMP D'ACTIONS BOUTONS
	
	JPanel modifyPanel_Match = new JPanel();
	GridBagConstraints gbc_modifyPanel_Match = new GridBagConstraints();
	gbc_modifyPanel_Match.fill = GridBagConstraints.BOTH;
	gbc_modifyPanel_Match.gridx = 1;
	gbc_modifyPanel_Match.gridy = 2;
	matchPanel.add(modifyPanel_Match, gbc_modifyPanel_Match);
	modifyPanel_Match.setLayout(new GridLayout(0, 1, 0, 0));
	
	JButton btnModifierReset_Match = new JButton("Reset");
	btnModifierReset_Match.setForeground(Color.MAGENTA);
	btnModifierReset_Match.setBackground(Color.YELLOW);
	btnModifierReset_Match.setFont(new Font("Lucida Console", Font.PLAIN, 11));
	btnModifierReset_Match.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	btnModifierReset_Match.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			textFieldFiltreNom_Match.setText(null);
			comboBoxFiltreSexe_Match.setSelectedItem(null);
			comboBoxFiltreTournoi_Match.setSelectedItem(null);
			comboBoxFiltreAnnee_Match.setSelectedItem(null);
			matchs = metierTennis.listerMatchs();
			refreshTableMatchs();
			System.out.println("resetMatch");
		}
	});
	modifyPanel_Match.add(btnModifierReset_Match);


//EPREUVES//////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////	
//////////////////////////////////////////////////////////////////////////////////////
	
	JPanel epreuvesPanel = new JPanel();
	epreuvesPanel.setFont(new Font("Lucida Console", Font.PLAIN, 11));
	epreuvesPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	tabbedPane.addTab("Epreuves", null, epreuvesPanel, null);
	tabbedPane.setForegroundAt(3, Color.MAGENTA);
	GridBagLayout gbl_epreuvesPanel = new GridBagLayout();
	gbl_epreuvesPanel.columnWidths = new int[]{500, 0, 0};
	gbl_epreuvesPanel.rowHeights = new int[]{85, 90, 178, 0};
	gbl_epreuvesPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
	gbl_epreuvesPanel.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
	epreuvesPanel.setLayout(gbl_epreuvesPanel);
	
	JScrollPane epreuveScrollPane = new JScrollPane();
	epreuveScrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
	GridBagConstraints gbc_epreuveScrollPane = new GridBagConstraints();
	gbc_epreuveScrollPane.gridheight = 3;
	gbc_epreuveScrollPane.insets = new Insets(0, 0, 0, 5);
	gbc_epreuveScrollPane.fill = GridBagConstraints.BOTH;
	gbc_epreuveScrollPane.gridx = 0;
	gbc_epreuveScrollPane.gridy = 0;
	epreuvesPanel.add(epreuveScrollPane, gbc_epreuveScrollPane);
	
	//SELECTION
	tableEpreuve = new JTable();
	tableEpreuve.addMouseListener(new MouseAdapter() {
		@Override
        public void mousePressed(MouseEvent e) {
//TODO Envoyer la selection vers les champs de modif
//				selectedEpreuve = epreuves.get(tableEpreuve.getSelectedRow());
//	            textFieldModifierNom_Match.setText(selectedMatch.getNomMatch());
//	            textFieldModifierNom_Match.setText(selectedMatch.getCodeMatch());
//	            textFieldModifierNom_Match.setText(selectedMatch.getCodeMatch());
	     }
	});
	tableEpreuve.setBackground(Color.LIGHT_GRAY);
	epreuveScrollPane.setViewportView(tableEpreuve);
	
	JPanel logoPanel_Epreuves = new JPanel();
	logoPanel_Epreuves.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	logoPanel_Epreuves.setBackground(Color.WHITE);
	GridBagConstraints gbc_logoPanel_Epreuves = new GridBagConstraints();
	gbc_logoPanel_Epreuves.insets = new Insets(0, 0, 5, 0);
	gbc_logoPanel_Epreuves.fill = GridBagConstraints.BOTH;
	gbc_logoPanel_Epreuves.gridx = 1;
	gbc_logoPanel_Epreuves.gridy = 0;
	epreuvesPanel.add(logoPanel_Epreuves, gbc_logoPanel_Epreuves);
	logoPanel_Epreuves.setLayout(new GridLayout(0, 1, 0, 0)); 
	logoLabel_Epreuve = new JLabel(image_Epreuve);
	logoLabel_Epreuve.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	logoPanel_Epreuves.add(logoLabel_Epreuve);
	
	
	JPanel searchFieldPanel_Epreuves = new JPanel();
	GridBagConstraints gbc_searchFieldPanel_Epreuves = new GridBagConstraints();
	gbc_searchFieldPanel_Epreuves.insets = new Insets(0, 0, 5, 0);
	gbc_searchFieldPanel_Epreuves.fill = GridBagConstraints.BOTH;
	gbc_searchFieldPanel_Epreuves.gridx = 1;
	gbc_searchFieldPanel_Epreuves.gridy = 1;
	epreuvesPanel.add(searchFieldPanel_Epreuves, gbc_searchFieldPanel_Epreuves);
	searchFieldPanel_Epreuves.setLayout(new GridLayout(0, 2, 0, 0));
	
	JLabel lblFiltreFiltrerPar_Epreuve = new JLabel("FILTRER PAR:");
	lblFiltreFiltrerPar_Epreuve.setForeground(Color.MAGENTA);
	lblFiltreFiltrerPar_Epreuve.setHorizontalAlignment(SwingConstants.LEFT);
	lblFiltreFiltrerPar_Epreuve.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblFiltreFiltrerPar_Epreuve.setBackground(Color.YELLOW);
	searchFieldPanel_Epreuves.add(lblFiltreFiltrerPar_Epreuve);
	
//FILTRAGE DE LA RECHERCHE
	JButton btnFiltrerPar_Epreuve = new JButton("GO!");
	btnFiltrerPar_Epreuve.setFont(new Font("Lucida Console", Font.PLAIN, 11));
	btnFiltrerPar_Epreuve.setForeground(Color.MAGENTA);
	btnFiltrerPar_Epreuve.setBackground(Color.YELLOW);
	btnFiltrerPar_Epreuve.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			epreuves = epreuves.stream()
					.filter(item ->(comboBoxFiltreSexe_Epreuve.getSelectedItem() == null) || (item.isMale() == comboBoxFiltreSexe_Epreuve.getSelectedItem().equals("H")))
					.filter(item ->(comboBoxFiltreTournoi_Epreuve.getSelectedItem() == null) || (item.getTournoi().getNomTournoi().equals(comboBoxFiltreTournoi_Epreuve.getSelectedItem())))
					.filter(item ->(comboBoxFiltreAnnee_Epreuve.getSelectedItem() == null) || (item.getAnneeEpreuve()==((int)comboBoxFiltreAnnee_Epreuve.getSelectedItem())))
					.collect(Collectors.toList());
			refreshTableEpreuves();
			System.out.println("recherche Epreuve");
		}
	});
	searchFieldPanel_Epreuves.add(btnFiltrerPar_Epreuve);
	
	JLabel lblFiltreSexe_Epreuve = new JLabel("SEXE: ");
	lblFiltreSexe_Epreuve.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	lblFiltreSexe_Epreuve.setHorizontalTextPosition(SwingConstants.CENTER);
	lblFiltreSexe_Epreuve.setForeground(Color.BLACK);
	lblFiltreSexe_Epreuve.setHorizontalAlignment(SwingConstants.RIGHT);
	lblFiltreSexe_Epreuve.setVerticalTextPosition(SwingConstants.BOTTOM);
	lblFiltreSexe_Epreuve.setFont(new Font("Lucida Console", Font.PLAIN, 14));
	searchFieldPanel_Epreuves.add(lblFiltreSexe_Epreuve);
	
	comboBoxFiltreSexe_Epreuve = new JComboBox(sexe);
	comboBoxFiltreSexe_Epreuve.setFont(new Font("Lucida Console", Font.PLAIN, 11));
	comboBoxFiltreSexe_Epreuve.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
	comboBoxFiltreSexe_Epreuve.setSelectedIndex(-1);
	searchFieldPanel_Epreuves.add(comboBoxFiltreSexe_Epreuve);
	
	JLabel lblFiltreTournoi_Epreuve = new JLabel("TOURNOI: ");
	lblFiltreTournoi_Epreuve.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	lblFiltreTournoi_Epreuve.setHorizontalTextPosition(SwingConstants.CENTER);
	lblFiltreTournoi_Epreuve.setForeground(Color.BLACK);
	lblFiltreTournoi_Epreuve.setHorizontalAlignment(SwingConstants.RIGHT);
	lblFiltreTournoi_Epreuve.setVerticalTextPosition(SwingConstants.BOTTOM);
	lblFiltreTournoi_Epreuve.setFont(new Font("Lucida Console", Font.PLAIN, 14));
	searchFieldPanel_Epreuves.add(lblFiltreTournoi_Epreuve);
	
	comboBoxFiltreTournoi_Epreuve = new JComboBox(optTournois.toArray());
	comboBoxFiltreTournoi_Epreuve.setFont(new Font("Lucida Console", Font.PLAIN, 11));
	comboBoxFiltreTournoi_Epreuve.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
	comboBoxFiltreTournoi_Epreuve.setSelectedIndex(-1);
	searchFieldPanel_Epreuves.add(comboBoxFiltreTournoi_Epreuve);
	
	JLabel lblFiltreAnnee_Epreuve = new JLabel("ANNÉE: ");
	lblFiltreAnnee_Epreuve.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	lblFiltreAnnee_Epreuve.setHorizontalTextPosition(SwingConstants.CENTER);
	lblFiltreAnnee_Epreuve.setForeground(Color.BLACK);
	lblFiltreAnnee_Epreuve.setHorizontalAlignment(SwingConstants.RIGHT);
	lblFiltreAnnee_Epreuve.setVerticalTextPosition(SwingConstants.BOTTOM);
	lblFiltreAnnee_Epreuve.setFont(new Font("Lucida Console", Font.PLAIN, 14));
	searchFieldPanel_Epreuves.add(lblFiltreAnnee_Epreuve);

	comboBoxFiltreAnnee_Epreuve = new JComboBox(optAnnees.toArray());
	comboBoxFiltreAnnee_Epreuve.setFont(new Font("Lucida Console", Font.PLAIN, 11));
	comboBoxFiltreAnnee_Epreuve.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
	comboBoxFiltreAnnee_Epreuve.setSelectedIndex(-1);
	searchFieldPanel_Epreuves.add(comboBoxFiltreAnnee_Epreuve);

	
//CHAMP DE MODIFICATION	


//CHAMP D'ACTIONS BOUTONS
	
	JPanel modifyPanel_Epreuve = new JPanel();
	GridBagConstraints gbc_modifyPanel_Epreuve = new GridBagConstraints();
	gbc_modifyPanel_Epreuve.fill = GridBagConstraints.BOTH;
	gbc_modifyPanel_Epreuve.gridx = 1;
	gbc_modifyPanel_Epreuve.gridy = 2;
	epreuvesPanel.add(modifyPanel_Epreuve, gbc_modifyPanel_Epreuve);
	modifyPanel_Epreuve.setLayout(new GridLayout(0, 1, 0, 0));
	
	JButton btnModifierReset_Epreuve = new JButton("Reset");
	btnModifierReset_Epreuve.setForeground(Color.MAGENTA);
	btnModifierReset_Epreuve.setBackground(Color.YELLOW);
	btnModifierReset_Epreuve.setFont(new Font("Lucida Console", Font.PLAIN, 11));
	btnModifierReset_Epreuve.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	btnModifierReset_Epreuve.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			comboBoxFiltreSexe_Epreuve.setSelectedItem(null);
			comboBoxFiltreTournoi_Epreuve.setSelectedItem(null);
			comboBoxFiltreAnnee_Epreuve.setSelectedItem(null);
			epreuves = metierTennis.listerEpreuves();
			refreshTableEpreuves();
			System.out.println("resetEpreuve");
		}
	});
	modifyPanel_Epreuve.add(btnModifierReset_Epreuve);
	
	}
}
