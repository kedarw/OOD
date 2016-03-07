package module6;

import java.util.List;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PGraphics;

/**
 * A class to represent AirportMarkers on a world map.
 *
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMarker extends CommonMarker {
	public static List<SimpleLinesMarker> routes;

	public AirportMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());

	}

	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		pg.fill(0,220,0);
		pg.ellipse(x, y, 9, 9);


	}

	@Override
	public void showTitle(PGraphics pg, float x, float y) {
		 // show rectangle with title
		pg.pushStyle();

		pg.textSize(12);
		pg.fill(0);

		String temp = getAirportName() + getAirportStartMonthAndYear();
		pg.text(temp, x, y);

		pg.popStyle();
		// show routes
	}

	private String getAirportName()
	{
		return getStringProperty("name");
	}

	private String getAirportStartMonthAndYear()
	{
		return getStringProperty("monthyear");
	}
}
