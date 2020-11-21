import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

class Card
{

    String suite;
    int value;

    String diffCard;

    Card(String theSuite, int theValue)
	{
		value =0;
        suite = theSuite;
		
		if (theValue == 1)
        {
            value = 1;               //Setting the Ace
            diffCard = "A";
        }
		
        else if(theValue > 9 )       //setting value of the card
        {
            if(theValue == 13)
                diffCard = "K";
			
			else if(theValue == 12)
                diffCard = "Q";
			
			else if(theValue == 11)
                diffCard = "J";

            else if(theValue == 10)
                diffCard = "10";
            
        }
        
		//theValue 2-9
        else
        {
            value = theValue;
            diffCard = Integer.toString(theValue);  //changing the int value into String
        }
    }
	
	int getValue()
    {
        return value;   //Value of the card that not the number The Value
    }
	
	
    public StackPane drawImage() 
	{

        // Concatenating the card number and suite to print respective card
        String cardPrinter = diffCard  +  suite + ".jpg";

        Image img = new Image(cardPrinter);

        //stack pane rectangle
        Rectangle rectangle = new Rectangle(120.0d, 170.0d);

        // Fill image in rectangle
        rectangle.setFill(new ImagePattern(img));

        // set rounded corners
        rectangle.setArcHeight(12.0d);
        rectangle.setArcWidth(12.0d);

        ImageView imageView = new ImageView(img);

        // create a Group
        StackPane recImg = new StackPane(rectangle);
        return recImg;
    }
}
