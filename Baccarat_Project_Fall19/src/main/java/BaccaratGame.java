//GURHARMANPREET SAMAGH
//netID- gsamag2

//SANKET SHAH
//netId - sshah307


//References
//https://casino.paddypower.com/game/baccarat-cptn for background image
//http://acbl.mybigcommerce.com/52-playing-cards/ for playing cards' image

import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;




public class BaccaratGame extends Application
{
	ArrayList<Card> playerHand;
	ArrayList<Card> bankerHand;
	BaccaratDealer theDealer;
	BaccaratGameLogic gameLogic;

	double currentBet;
	double totalWinnings;

	Scene scene1, scene2;

	int timeCounter = 0;
	
	double tieBid = 0, playerBid = 0, bankerBid = 0;
	
	int playerTotal = 0;
	int bankerTotal = 0;



	public static void main(String args[])
	{
		// launch the application
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception
	{
		//Title of the application
		primaryStage.setTitle("Project Baccarat"); 
		
		//Scene 1 with label 1 and btn - Welcome to Baccarat and Play Game
		BorderPane mypane = new BorderPane();
		
		//Declaring and Initializing label 1 -  Welcome to Baccarat
		Label label1 = new Label("\t\t\t\t  Welcome to Baccarat ");
		label1.setTextFill(Color.GOLD);
		label1.setFont(new Font("Abel", 30));
		
		//Declaring and Initializing btn -  Play Game
		Button btn = new Button(" Play Game ");
		btn.setFont(new Font("Abel", 18));
		
		//Set Welcome to Baccarat to hBoxWinBidTxt
		mypane.setTop(label1);
		
		//Set Play Game to hBoxAmount
		mypane.setCenter(btn);
		
		
		//Start of Scene 2 implementation
		theDealer = new BaccaratDealer();
		gameLogic = new BaccaratGameLogic();
		
        //create new deck of card
		theDealer.generateDeck(); 
		
		//randomly mix the new deck
		theDealer.shuffleDeck(); 
		
		//Creation, declaration and initialization of borderpane
		BorderPane bPane = new BorderPane();
		
        //DrawHand, Draw Buttons
        //DrawHand Button for second use
		Button dealBtn = new Button("DEAL HAND");
		//Draw Button for second use
		Button drawBtn = new Button("DRAW");
		
		//Bidding Buttons
		Button btn5 = new Button("$5");
		Button btn25 = new Button("$25");
		Button btn100 = new Button("$100");
		Button btn500 = new Button("$500");
		
		//Player, Banker and Tie Buttons
		Button playerBtn = new Button("PLAYER");
		Button bankerBtn = new Button("BANKER");
		Button aDrawBtn = new Button ("A DRAW");

        //Text Field for total money
		TextField winningTotalText = new TextField("10000");
		winningTotalText.setPrefWidth(60);
		
		//Text Field for amount we are betAmtLbl
		TextField biddingTotalText = new TextField("");				
		biddingTotalText.setPrefWidth(60);


		//LABELS
		Label winningTotalLbl = new Label("Winning Total");
		winningTotalLbl.setTextFill(Color.GOLD);
		winningTotalLbl.setFont(new Font("Abel", 18));

		Label biddingTotalLbl = new Label("Bidding Total");
		biddingTotalLbl.setTextFill(Color.GOLD);
		biddingTotalLbl.setFont(new Font("Abel", 18));
		
		Label winnerLbl = new Label("");
		winnerLbl.setTextFill(Color.GOLD);
		winnerLbl.setFont(new Font("Abel", 18));

		Label betAmtLbl = new Label("Bet Amount");
		betAmtLbl.setTextFill(Color.GOLD);
		betAmtLbl.setFont(new Font("Abel", 18));

		Label bidderLbl = new Label("Bidder");
		bidderLbl.setTextFill(Color.GOLD);
		bidderLbl.setFont(new Font("Abel", 18));

		Label actionLbl = new Label("Action");
		actionLbl.setTextFill(Color.GOLD);
		actionLbl.setFont(new Font("Abel", 18));

		Label playerScored = new Label("Player Score: " + Integer.toString(playerTotal)); //I change the int to string..
		playerScored.setTextFill(Color.GOLD);
		playerScored.setFont(new Font("Abel", 18));

		Label bankerScored = new Label("Banker Score: " + Integer.toString(bankerTotal)); //to print it out
		bankerScored.setTextFill(Color.GOLD);
		bankerScored.setFont(new Font("Abel", 18));
		
		

		HBox hBoxMain = new HBox(50); //setting an HBox

		//Bid types
		VBox vBox1 = new VBox(10);
		vBox1.setAlignment(Pos.TOP_LEFT);
		vBox1.getChildren().add(bPane);  //adding the Menu
		

		//Bidding buttons Vbox
		VBox betVBox = new VBox(10);
		betVBox.setAlignment(Pos.CENTER);
		betVBox.getChildren().addAll(actionLbl,dealBtn,drawBtn);
		
		VBox change = new VBox(300, vBox1, betVBox);

		
		
		//Player, banker, draw button Vbox
		VBox vBox2 = new VBox(10);
		//adding the bidders and this where the user will  choose whom to bid on
		vBox2.getChildren().addAll(bidderLbl, playerBtn, bankerBtn, aDrawBtn);
		vBox2.setAlignment(Pos.CENTER);
		
		
		
		//player's Score Vbox
		VBox vPlayer = new VBox();
		vPlayer.getChildren().add(playerScored);  //Player Score add
		vPlayer.setAlignment(Pos.TOP_CENTER);


		//This part of vBox is where the Banker will get the cards and print the Banker's score
		VBox vBanker = new VBox();
		vBanker.getChildren().add(bankerScored);
		vBanker.setAlignment(Pos.TOP_CENTER);
		
		
		
		//Bidding Bidding and Winning label and text and wining and bidding amounbt , Deal Draw buttons Vbox
		VBox vBoxMiddle = new VBox(20);
		vBoxMiddle.setAlignment(Pos.BOTTOM_CENTER);
		
		
		
		VBox vBoxWinner = new VBox(400);
		vBoxWinner.getChildren().add(winnerLbl);
		vBoxWinner.setAlignment(Pos.CENTER);

		//These are Hbox I am making it to fit them in pane properly
		HBox hBoxWinBidLbl = new HBox(20);
		
		//winning amount and bidding amount label
		hBoxWinBidLbl.getChildren().addAll(winningTotalLbl,biddingTotalLbl);
		hBoxWinBidLbl.setAlignment(Pos.CENTER);
		
		HBox hBoxWinBidTxt = new HBox(80);
		//winning amount and bidding amount text
		hBoxWinBidTxt.getChildren().addAll(winningTotalText,biddingTotalText);
		hBoxWinBidTxt.setAlignment(Pos.CENTER);
		
		//Bidding amount buttons
		HBox hBoxAmount = new HBox(5);
		hBoxAmount.getChildren().addAll(betAmtLbl, btn5, btn25, btn100, btn500);
		hBoxAmount.setAlignment(Pos.CENTER);
		

		//adding the vBoxWinner winner,  winning/bidding amount label and text and options amount bidding
		vBoxMiddle.getChildren().addAll(vBoxWinner, hBoxWinBidLbl,hBoxWinBidTxt,hBoxAmount);


		

		dealBtn.setDisable(true);
		aDrawBtn.setDisable(true);
		playerBtn.setDisable(true);
		bankerBtn.setDisable(true);
		
		
		
		//MENU BAR IMPLEMENTATION
		//Menu Creation with name Options
		Menu menuOp = new Menu("Options");
		
		//Options in the menu option(Menu Items) - Exit and Fresh Start
		MenuItem freshStart = new MenuItem("Fresh Start");
		MenuItem exit = new MenuItem("Exit");			
		
		//Adding freshstart and exit(Menu Items) into the options(Menu)
		menuOp.getItems().add(freshStart);     
		menuOp.getItems().add(exit);
        
		
		//Inserting visible menu option (Options) on application
		MenuBar menuBar = new MenuBar(menuOp);	//setting MenuBar
		bPane.setTop(menuBar);				//adding the menu on hBoxWinBidTxt of the application
		
		//closing the application
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
			primaryStage.close();
			}
		});
		
		freshStart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
			//resetting everything
			try 
			{
				playerTotal = 0;
				bankerTotal = 0;
				totalWinnings = 10000;			
				playerScored.setText("Player Score: " + Integer.toString(playerTotal));
				bankerScored.setText("Banker Score: " + Integer.toString(bankerTotal));

				freshStart(primaryStage);
			} 
			
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
			}
		});
		
		totalWinnings = 10000;
		drawBtn.setDisable(true);
		

		btn5.setOnAction(e -> {
			    //set the current bidding amount
				currentBet = currentBet + 5; 
				    
				aDrawBtn.setDisable(false);
				bankerBtn.setDisable(false);
				playerBtn.setDisable(false);
				
				//update bidding total
				biddingTotalText.setText(Double.toString(currentBet)); 
		});
		btn25.setOnAction(e -> {
			    //set the current bidding amount
				currentBet = currentBet + 25; 
				
				aDrawBtn.setDisable(false);
				bankerBtn.setDisable(false);
				playerBtn.setDisable(false);
				
				//update bidding total
				biddingTotalText.setText(Double.toString(currentBet)); 
		});
		btn100.setOnAction(e -> {
			  //set the current bidding amount
				currentBet = currentBet + 100; 
				
				aDrawBtn.setDisable(false);
				bankerBtn.setDisable(false);
				playerBtn.setDisable(false);
				
				//update bidding total
				biddingTotalText.setText(Double.toString(currentBet)); 
		});
		btn500.setOnAction(e -> {
			    //set the current bidding amount
				currentBet = currentBet + 500; 
				
				aDrawBtn.setDisable(false);
				bankerBtn.setDisable(false);
				playerBtn.setDisable(false);
				
				biddingTotalText.setText(Double.toString(currentBet)); 
		});

		
		bankerBtn.setOnAction(e -> {
				
				bankerBid = currentBet; //get bidding total
				totalWinnings = totalWinnings - bankerBid; //subtract from all the total
				winningTotalText.setText(Double.toString(totalWinnings));
				
				//Finalise the betVBox by playerBtn and set currentBet field = 0 for playerBtn
				biddingTotalText.setText("");
				currentBet = 0;
				dealBtn.setDisable(false);
		});
		
		aDrawBtn.setOnAction(e -> {
				
				tieBid = currentBet; //get bidding total
				totalWinnings = totalWinnings - tieBid; //subtract from all the total
				winningTotalText.setText(Double.toString(totalWinnings));
				
				//Finalise the betVBox by playerBtn and bankerBtn, and set currentBet field = 0
				biddingTotalText.setText("");
				currentBet = 0;
				dealBtn.setDisable(false);
		});
		
		playerBtn.setOnAction(e -> {
				
				playerBid = currentBet;		//get bidding total
				totalWinnings = totalWinnings - playerBid;//subtract from all the total
				winningTotalText.setText(Double.toString(totalWinnings));
				
				//Finalise the betVBox by playerBtn and set currentBet field = 0 for bankerBtn
				biddingTotalText.setText("");
				currentBet = 0;
				dealBtn.setDisable(false);
		});

		
		dealBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
			if(theDealer.deckSize() < 6)//if deck is empty
			{
				theDealer.emptyDeck();
				theDealer.generateDeck();
			}
			
			
			playerHand = theDealer.dealHand();
			playerTotal = gameLogic.handTotal(playerHand);
			bankerHand = theDealer.dealHand();
			bankerTotal = gameLogic.handTotal(bankerHand);

			timeCounter = 0;

			drawBtn.setDisable(true);
			dealBtn.setDisable(true);
			
			//initialize pause transition in a loop
			PauseTransition pause = new PauseTransition(Duration.seconds(0.5)); 
			pause.setOnFinished(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) 
			{
				timeCounter = timeCounter + 1;
				if(timeCounter == 2)
				{
					playerScored.setText("Player Score: " + Integer.toString(playerHand.get(0).getValue()));
					vPlayer.getChildren().add(playerHand.get(0).drawImage()); //1st card for player
				}
				
				if(timeCounter == 3)
				{
					bankerScored.setText("Banker Score: " + Integer.toString(bankerHand.get(0).getValue()));
					vBanker.getChildren().add(bankerHand.get(0).drawImage()); //1st card for banker
				}
				
				if(timeCounter == 4)
				{
					playerScored.setText("Player Score: " + Integer.toString(playerTotal));
					vPlayer.getChildren().add(playerHand.get(1).drawImage()); //2nd card for player
				}
				
				if(timeCounter == 5)
				{
					bankerScored.setText("Banker Score: " + Integer.toString(bankerTotal));
					vBanker.getChildren().add(bankerHand.get(1).drawImage()); //2nd card for banker
				}
				
				if(timeCounter > 5 && gameLogic.checkWinIntDraw(playerTotal, bankerTotal))
				{
						drawBtn.setDisable(true);
						
						totalWinnings = evaluateWinnings();
						winningTotalText.setText(Double.toString(evaluateWinnings()));
						winnerLbl.setText(gameLogic.whoWon(playerHand, bankerHand));
						return;
				}
				
				if(timeCounter > 5 && !gameLogic.checkWinIntDraw(playerTotal, bankerTotal))
				{
					dealBtn.setDisable(true);
					drawBtn.setDisable(false);
					return;
				}
				pause.play();
			}});
			pause.play();
			}
		});

		drawBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				timeCounter = 0;
				//initialize pause transition in a loop
				PauseTransition pause = new PauseTransition(Duration.seconds(1)); 

				pause.setOnFinished(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						
						timeCounter = timeCounter + 1;
						//if player draws
						if (timeCounter == 2 && gameLogic.evaluatePlayerDraw(playerHand)) 
						{
							playerHand.add(theDealer.drawOne());
							playerTotal = gameLogic.handTotal(playerHand);
							playerScored.setText("Player Score: " + Integer.toString(playerTotal)); 
							vPlayer.getChildren().add(playerHand.get(2).drawImage());     
						}
						
						//if banker draws
						if (timeCounter == 3 && gameLogic.evaluateBankerDraw(bankerHand, playerHand.get(playerHand.size() - 1))) 
						{        
							bankerHand.add(theDealer.drawOne());
							bankerTotal = gameLogic.handTotal(bankerHand); 
							bankerScored.setText("Banker Score: " + Integer.toString(bankerTotal));
							vBanker.getChildren().add(bankerHand.get(2).drawImage());
						}
						
						if (timeCounter == 4) 
						{
							totalWinnings = evaluateWinnings();
							winningTotalText.setText(Double.toString(evaluateWinnings()));
							winnerLbl.setText(gameLogic.whoWon(playerHand, bankerHand));
							return;            //return
						}
						pause.play();
					}
				});
				drawBtn.setDisable(true);
				dealBtn.setDisable(true);
				pause.play();
			}
		});

	
        //Scene1 creation
        scene1 = new Scene(mypane, 850, 800);
		
		btn.setOnAction(e->primaryStage.setScene(scene2));
		
		//setting all vbox into Hbox
		hBoxMain.getChildren().addAll(change, vPlayer, vBoxMiddle, vBanker, vBox2);
		scene2 = new Scene(hBoxMain, 850, 800);

		//Creating Background Image
		BackgroundImage myBI= new BackgroundImage(new Image("baccarat-table.jpg",800,800,false,true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
        
		//then you set to your node
		mypane.setBackground(new Background(myBI));
		hBoxMain.setBackground(new Background(myBI));
		
		primaryStage.setScene(scene1);
		primaryStage.show();
	}

	//determine if the user won or lost and return total balance the amount won or lost 
	public double evaluateWinnings()
	{
		//Ties
		if(gameLogic.whoWon(playerHand, bankerHand) == "Tied")
		{
			return(totalWinnings + tieBid*8);
		}
		//Player wins
		else if(gameLogic.whoWon(playerHand, bankerHand) == "Player Wins")
		{
			return (totalWinnings + playerBid*2); 
		}

        //Banker wins
		else
			return(totalWinnings + bankerBid*2);
	}

	//restarting
	public void freshStart(Stage primaryStage) throws Exception {
		start(primaryStage);
	}

}