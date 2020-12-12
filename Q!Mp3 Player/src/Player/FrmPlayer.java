/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import jaco.mp3.player.MP3Player;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author Junior
 */
public class FrmPlayer extends javax.swing.JFrame {

   MP3Player player;
   File musica;
   String diretorioAtual = "home.user";
   String pathAtual;
   String pathImagem;
   boolean repetir;
   
   //Coordenadas mouse
   int xx = 0;
   int yy = 0;
    
    public FrmPlayer() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Botões/mp3_icon.png")));
        
        txtNomeMusica.setEditable(false);
        //Mensagem de boas vindas ao usuário(nome de usuario do sistema)
        txtNomeMusica.setText("SEJA BEM VINDO " + System.getProperty("user.name").toUpperCase());
        
        player = new MP3Player(); //Mp3 player pronto para ser executado
       
    }
    
   
    //Controles de volume Baixo
    private void volumeBaixo(Double valorMaisMenos){
        //Pegar informações do mixer do sistema do usuário
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        for(Mixer.Info mixerInfo : mixers){
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            
            for(Line.Info lineInfo : lineInfos){
                Line line = null;
                
                boolean aberto = true;
                
                try {
                    line = mixer.getLine(lineInfo);
                    aberto = line.isOpen() || line instanceof Clip;
                    
                    if(!aberto){
                        line.open();
                    }
                    
                    //FloatControl
                    FloatControl controleVol = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    
                    float volumeAtual = controleVol.getValue();
                    
                    Double volumeMenos = valorMaisMenos;
                    
                    //Calcular adição ou subtração de volume (diminuir)
                    float mudarCalc = (float) ((float) volumeAtual - (double) volumeMenos);
                    
                    //Inserir este valor no volume
                    controleVol.setValue(mudarCalc);
                } catch(LineUnavailableException ex){
                    ex.printStackTrace();
                } catch (IllegalArgumentException ex){
                    ex.printStackTrace();
                } finally {
                    if(line != null && !aberto){
                        line.close();      
                    }
                }
            }
        }
        
    }
    
     //Controles de volume Cima
    private void volumeCima(Double valorMaisMenos){
        //Pegar informações do mixer do sistema do usuário
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        for(Mixer.Info mixerInfo : mixers){
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            
            for(Line.Info lineInfo : lineInfos){
                Line line = null;
                
                boolean aberto = true;
                
                try {
                    line = mixer.getLine(lineInfo);
                    aberto = line.isOpen() || line instanceof Clip;
                    
                    if(!aberto){
                        line.open();
                    }
                    
                    //FloatControl
                    FloatControl controleVol = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    
                    float volumeAtual = controleVol.getValue();
                    
                    Double volumeMenos = valorMaisMenos;
                    
                    //Calcular adição ou subtração de volume(aumentar)
                    float mudarCalc = (float) ((float) volumeAtual + (double) volumeMenos);
                    
                    //Inserir este valor no volume
                    controleVol.setValue(mudarCalc);
                } catch(LineUnavailableException ex){
                    ex.printStackTrace();
                } catch (IllegalArgumentException ex){
                    ex.printStackTrace();
                } finally {
                    if(line != null && !aberto){
                        line.close();      
                    }
                }
            }
        }
        
    }
    
     //Controles de volume 
    private void volumeControle(Double valorMaisMenos){
        //Pegar informações do mixer do sistema do usuário
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        for(Mixer.Info mixerInfo : mixers){
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            
            for(Line.Info lineInfo : lineInfos){
                Line line = null;
                
                boolean aberto = true;
                
                try {
                    line = mixer.getLine(lineInfo);
                    aberto = line.isOpen() || line instanceof Clip;
                    
                    if(!aberto){
                        line.open();
                    }
                    
                    //FloatControl
                    FloatControl controleVol = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    
                    float volumeAtual = controleVol.getValue();
                    
                    Double volumeMenos = valorMaisMenos;
                    
                    //Calcular adição ou subtração de volume
                    float mudarCalc = (float) ((double) volumeMenos);
                    
                    //Inserir este valor no volume
                    controleVol.setValue(mudarCalc);
                } catch(LineUnavailableException ex){
                    ex.printStackTrace();
                } catch (IllegalArgumentException ex){
                    ex.printStackTrace();
                } finally {
                    if(line != null && !aberto){
                        line.close();      
                    }
                }
            }
        }
        
    }
    
    private void abrirDocumentacao() {
        
        if(Desktop.isDesktopSupported()) { 
            
             try {
                
                // Vamos verifcar se o sistema é x86 ou amd64
                final String osType = System.getenv("PROCESSOR_ARCHITECTURE");
                     
                if(osType.equals("AMD64")) {
                    
                    final String progamFiles = System.getenv("programfiles(x86)");
                    final String path = progamFiles + "\\Quantum Comp IT Solutions\\Q! Mp3 Player\\Q!Mp3Player-documentacao.pdf";
                    File doc = new File(path);
                    Desktop.getDesktop().open(doc);
                
                } else {
                    
                    final String progamFiles = System.getenv("programfiles");
                    final String path = progamFiles + "\\Quantum Comp IT Solutions\\Q! Mp3 Player\\Q!Mp3Player-documentacao.pdf";
                    File doc = new File(path);
                    Desktop.getDesktop().open(doc);
                    
                }
                
            } catch (Exception e) {
                e.toString();
            }
          
            
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panNomeApp = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        lblSettings = new javax.swing.JLabel();
        lblOff = new javax.swing.JLabel();
        panMusica = new javax.swing.JPanel();
        panControles = new javax.swing.JPanel();
        lblRepeat = new javax.swing.JLabel();
        lblPause = new javax.swing.JLabel();
        lblPlay = new javax.swing.JLabel();
        lblStop = new javax.swing.JLabel();
        lblOpen = new javax.swing.JLabel();
        lblVolUp = new javax.swing.JLabel();
        lblMute = new javax.swing.JLabel();
        lblVolMax = new javax.swing.JLabel();
        lblVolDown = new javax.swing.JLabel();
        txtNomeMusica = new javax.swing.JTextField();
        lblMinimize = new javax.swing.JLabel();
        lblAbout = new javax.swing.JLabel();
        lblNext = new javax.swing.JLabel();
        lblPrevious = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Q! Mp3 Player");
        setUndecorated(true);
        setResizable(false);

        panNomeApp.setBackground(new java.awt.Color(7, 63, 86));
        panNomeApp.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panNomeAppMouseDragged(evt);
            }
        });
        panNomeApp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panNomeAppMousePressed(evt);
            }
        });

        lblNome.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNome.setForeground(new java.awt.Color(34, 202, 237));
        lblNome.setText("Q! MP3 PLAYER");

        lblSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Player/Botões/settings.png"))); // NOI18N
        lblSettings.setToolTipText("Cor do Display");
        lblSettings.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSettingsMouseClicked(evt);
            }
        });

        lblOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Player/Botões/quit.png"))); // NOI18N
        lblOff.setToolTipText("Desligar");
        lblOff.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblOff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOffMouseClicked(evt);
            }
        });

        panMusica.setBackground(new java.awt.Color(7, 63, 86));

        panControles.setBackground(new java.awt.Color(7, 63, 86));

        lblRepeat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Player/Botões/repeat.png"))); // NOI18N
        lblRepeat.setToolTipText("Repetir Músicas");
        lblRepeat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblRepeat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRepeatMouseClicked(evt);
            }
        });

        lblPause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Player/Botões/pause.png"))); // NOI18N
        lblPause.setToolTipText("Pausar Música");
        lblPause.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblPause.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPauseMouseClicked(evt);
            }
        });

        lblPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Player/Botões/play.png"))); // NOI18N
        lblPlay.setToolTipText("Tocar Músicas");
        lblPlay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPlayMouseClicked(evt);
            }
        });

        lblStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Player/Botões/stop.png"))); // NOI18N
        lblStop.setToolTipText("Parar Música");
        lblStop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblStop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStopMouseClicked(evt);
            }
        });

        lblOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Player/Botões/open.png"))); // NOI18N
        lblOpen.setToolTipText("Adicionar Música à Playslist");
        lblOpen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblOpen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOpenMouseClicked(evt);
            }
        });

        lblVolUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Player/Botões/volume_up.png"))); // NOI18N
        lblVolUp.setToolTipText("Volume +");
        lblVolUp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblVolUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVolUpMouseClicked(evt);
            }
        });

        lblMute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Player/Botões/mute.png"))); // NOI18N
        lblMute.setToolTipText("Mudo");
        lblMute.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMute.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMuteMouseClicked(evt);
            }
        });

        lblVolMax.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Player/Botões/volume_full.png"))); // NOI18N
        lblVolMax.setToolTipText("Volume Máximo");
        lblVolMax.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblVolMax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVolMaxMouseClicked(evt);
            }
        });

        lblVolDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Player/Botões/volume_down.png"))); // NOI18N
        lblVolDown.setToolTipText("Volume -");
        lblVolDown.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblVolDown.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVolDownMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panControlesLayout = new javax.swing.GroupLayout(panControles);
        panControles.setLayout(panControlesLayout);
        panControlesLayout.setHorizontalGroup(
            panControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panControlesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRepeat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPause)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPlay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOpen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblVolDown)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVolUp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMute)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVolMax)
                .addGap(14, 14, 14))
        );
        panControlesLayout.setVerticalGroup(
            panControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panControlesLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(panControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panControlesLayout.createSequentialGroup()
                        .addGroup(panControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblOpen)
                            .addComponent(lblStop)
                            .addComponent(lblPause)
                            .addComponent(lblRepeat))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panControlesLayout.createSequentialGroup()
                        .addGroup(panControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVolUp)
                            .addComponent(lblMute)
                            .addComponent(lblVolMax)
                            .addComponent(lblVolDown))
                        .addGap(30, 30, 30))
                    .addComponent(lblPlay, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        txtNomeMusica.setEditable(false);
        txtNomeMusica.setBackground(new java.awt.Color(7, 63, 86));
        txtNomeMusica.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        txtNomeMusica.setForeground(new java.awt.Color(34, 202, 237));
        txtNomeMusica.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNomeMusica.setText("REPRODUZINDO");
        txtNomeMusica.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(34, 202, 237)));

        javax.swing.GroupLayout panMusicaLayout = new javax.swing.GroupLayout(panMusica);
        panMusica.setLayout(panMusicaLayout);
        panMusicaLayout.setHorizontalGroup(
            panMusicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panControles, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panMusicaLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(txtNomeMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panMusicaLayout.setVerticalGroup(
            panMusicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panMusicaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(txtNomeMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panControles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Player/Botões/minimize.png"))); // NOI18N
        lblMinimize.setToolTipText("Minimizar");
        lblMinimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseClicked(evt);
            }
        });

        lblAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Player/Botões/about.png"))); // NOI18N
        lblAbout.setToolTipText("Ajuda");
        lblAbout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAboutMouseClicked(evt);
            }
        });

        lblNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Player/Botões/next.png"))); // NOI18N
        lblNext.setToolTipText("Próxima Música");
        lblNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNextMouseClicked(evt);
            }
        });

        lblPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Player/Botões/previous.png"))); // NOI18N
        lblPrevious.setToolTipText("Música Anterior");
        lblPrevious.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblPrevious.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPreviousMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panNomeAppLayout = new javax.swing.GroupLayout(panNomeApp);
        panNomeApp.setLayout(panNomeAppLayout);
        panNomeAppLayout.setHorizontalGroup(
            panNomeAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panNomeAppLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAbout)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPrevious)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNext)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMinimize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSettings)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblOff)
                .addGap(15, 15, 15))
            .addGroup(panNomeAppLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panMusica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panNomeAppLayout.setVerticalGroup(
            panNomeAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panNomeAppLayout.createSequentialGroup()
                .addGroup(panNomeAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panNomeAppLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblNome))
                    .addGroup(panNomeAppLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panNomeAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSettings, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblOff)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panNomeAppLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panNomeAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMinimize, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblAbout, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNext, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPrevious, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panMusica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panNomeApp, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panNomeApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Iniciar musica
    private void lblPlayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPlayMouseClicked
        player.play();
        txtNomeMusica.setText("Tocando ...  :  Minha Playlist");
    }//GEN-LAST:event_lblPlayMouseClicked

    //Parar musica
    private void lblStopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStopMouseClicked
        player.stop();
        txtNomeMusica.setText("Parado  :  Minha Playlist");
    }//GEN-LAST:event_lblStopMouseClicked

    //Pausar musica
    private void lblPauseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPauseMouseClicked
        player.pause();
        txtNomeMusica.setText("Pausado  :  Minha Playlist");
    }//GEN-LAST:event_lblPauseMouseClicked

    //Repetir musica
    private void lblRepeatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRepeatMouseClicked
        if(!repetir) {
            repetir = true;
            player.setRepeat(repetir);
            
            // Mudar icone da label repetir
            lblRepeat.setIcon(new ImageIcon(getClass().getResource("Botões/repeat_enabled.png")));
        }
        else {
            repetir = false;
            player.setRepeat(repetir);
            
            // Mudar icone da label repetir
            lblRepeat.setIcon(new ImageIcon(getClass().getResource("Botões/repeat.png")));
        }
    }//GEN-LAST:event_lblRepeatMouseClicked

    //Procurar música a ser tocada
    private void lblOpenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOpenMouseClicked
        //Escolher arquivos de musica
        JFileChooser escolher = new JFileChooser(diretorioAtual);
        escolher.setFileFilter(new FileTypeFilter("mp3", "Apenas mp3"));
        int resultado = escolher.showOpenDialog(null);
        if(resultado == JFileChooser.APPROVE_OPTION){
            //Musica selecionada será adicionada ao player
            musica = escolher.getSelectedFile();
            player.addToPlayList(musica);
            player.skipForward();
            diretorioAtual = musica.getAbsolutePath();
            txtNomeMusica.setText(musica.getName());
            player.stop();
        }
    }//GEN-LAST:event_lblOpenMouseClicked

    private void lblOffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOffMouseClicked
        this.dispose();
    }//GEN-LAST:event_lblOffMouseClicked

    private void lblSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSettingsMouseClicked
        // Mudar cor do "LCD"
        if(evt.getClickCount() == 1) {
            txtNomeMusica.setBackground(new Color(34, 202, 237));
            txtNomeMusica.setForeground(new Color(7, 63, 86));
        } else if(evt.getClickCount() == 2) {
            txtNomeMusica.setBackground(new Color(255, 192, 3));
            txtNomeMusica.setForeground(new Color(7, 63, 86));
        } else {
            txtNomeMusica.setBackground(new Color(7, 63, 86));
            txtNomeMusica.setForeground(new Color(34, 202, 237));
        }
       
    }//GEN-LAST:event_lblSettingsMouseClicked

    private void lblMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseClicked
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_lblMinimizeMouseClicked

    private void lblVolDownMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolDownMouseClicked
        volumeBaixo(0.1);
    }//GEN-LAST:event_lblVolDownMouseClicked

    private void lblVolUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolUpMouseClicked
        volumeCima(0.1);
    }//GEN-LAST:event_lblVolUpMouseClicked

    private void lblMuteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMuteMouseClicked
        volumeControle(0.0);
    }//GEN-LAST:event_lblMuteMouseClicked

    private void lblVolMaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolMaxMouseClicked
        volumeControle(1.0);
    }//GEN-LAST:event_lblVolMaxMouseClicked

    private void lblAboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAboutMouseClicked
        abrirDocumentacao();
    }//GEN-LAST:event_lblAboutMouseClicked

    private void lblNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNextMouseClicked
        player.skipForward();
        txtNomeMusica.setText("Tocando ...  :  Minha Playlist");
    }//GEN-LAST:event_lblNextMouseClicked

    private void lblPreviousMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPreviousMouseClicked
        player.skipBackward();
        txtNomeMusica.setText("Tocando ...  :  Minha Playlist");
    }//GEN-LAST:event_lblPreviousMouseClicked

    private void panNomeAppMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panNomeAppMousePressed
        xx = evt.getX();
        yy = evt.getY();
    }//GEN-LAST:event_panNomeAppMousePressed

    private void panNomeAppMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panNomeAppMouseDragged
        // Arrastar JFrame 
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x -xx, y-yy);
    }//GEN-LAST:event_panNomeAppMouseDragged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPlayer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblAbout;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblMute;
    private javax.swing.JLabel lblNext;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblOff;
    private javax.swing.JLabel lblOpen;
    private javax.swing.JLabel lblPause;
    private javax.swing.JLabel lblPlay;
    private javax.swing.JLabel lblPrevious;
    private javax.swing.JLabel lblRepeat;
    private javax.swing.JLabel lblSettings;
    private javax.swing.JLabel lblStop;
    private javax.swing.JLabel lblVolDown;
    private javax.swing.JLabel lblVolMax;
    private javax.swing.JLabel lblVolUp;
    private javax.swing.JPanel panControles;
    private javax.swing.JPanel panMusica;
    private javax.swing.JPanel panNomeApp;
    private javax.swing.JTextField txtNomeMusica;
    // End of variables declaration//GEN-END:variables
}
