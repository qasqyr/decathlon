import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomTest {

    @Test
    public void checkTotalPoints() {
        Competitor competitor1 = new Competitor(
                "Kevin Mayer;10.55;7.80;16.00;2.05;48.42;13.75;50.54;5.45;71.90;4.36.11".split(";")
        );
        Assert.assertEquals(9126, competitor1.getTotalPoint());
        Competitor competitor2 = new Competitor(
                "Ashton Eaton;10.23;7.88;14.52;2.01;45.00;13.69;43.34;5.20;63.63;4.17.52".split(";")
        );
        Assert.assertEquals(9045, competitor2.getTotalPoint());
    }

    @Test
    public void checkCompetitorsPlace() {
        Competitor competitor1 = new Competitor(
                "Kevin Mayer;10.55;7.80;16.00;2.05;48.42;13.75;50.54;5.45;71.90;4.36.11".split(";")
        );
        Competitor competitor2 = new Competitor(
                "Kevin Mayer;10.55;7.80;16.00;2.05;48.42;13.75;50.54;5.45;71.90;4.36.11".split(";")
        );
        Competitor competitor3 = new Competitor(
                "Kevin Mayer;10.55;7.80;16.00;2.05;48.42;13.75;50.54;5.45;71.90;4.36.11".split(";")
        );
        Competitor competitor4 = new Competitor(
                "Ashton Eaton;10.23;7.88;14.52;2.01;45.00;13.69;43.34;5.20;63.63;4.17.52".split(";")
        );
        Competitor competitor5 = new Competitor(
                "Ashton Eaton;10.23;7.88;14.52;2.01;45.00;13.69;43.34;5.20;63.63;4.17.52".split(";")
        );
        Competitor competitor6 = new Competitor(
                "Ashton Eaton;10.23;7.88;14.52;2.01;45.00;13.69;43.34;5.20;63.63;4.17.52".split(";")
        );

        ArrayList<Competitor> competitors = new ArrayList<>(List.of(
                competitor1, competitor2, competitor3, competitor4, competitor5, competitor6
        ));

        Collections.sort(competitors);
        Collections.reverse(competitors);
        CompetitionUtils.calculateCompetitorsPlace(competitors);

        Assert.assertEquals("1-3", competitor1.getPlace());
        Assert.assertEquals("1-3", competitor2.getPlace());
        Assert.assertEquals("1-3", competitor3.getPlace());
        Assert.assertEquals("4-6", competitor4.getPlace());
        Assert.assertEquals("4-6", competitor5.getPlace());
        Assert.assertEquals("4-6", competitor6.getPlace());
    }

}
