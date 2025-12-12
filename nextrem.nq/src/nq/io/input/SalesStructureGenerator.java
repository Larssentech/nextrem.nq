// (c) 2006-2007 NextremSoft

package nq.io.input;

import nq.sales.*;
import utilities.io.*;

public class SalesStructureGenerator {

  public SalesStructureGenerator() {
  }

  public SalesTerritory generateSalesStructureFromFile(String fileName) {
    SalesTerritory salesTerritory = new SalesTerritory();
    String[][] btdMatrix = new ArrayBuilder().makeArrayFromTSVExcludeLines(fileName, 2);
    for (int i = 0; i < btdMatrix.length; i++) {
      // RMs
      RegionalManager rm = new RegionalManager();
      rm.setName(btdMatrix[i][17]);
      salesTerritory.addRm(rm);
      // RMs are also TMs
      salesTerritory.addTm(rm);
      // TMs
      TerritoryManager tmCB = new TerritoryManager();
      tmCB.setName(btdMatrix[i][18]);
      salesTerritory.addTm(tmCB);

      TerritoryManager tmWHE = new TerritoryManager();
      tmWHE.setName(btdMatrix[i][19]);
      salesTerritory.addTm(tmWHE);
      // Country
      Country thisCountry = new Country(btdMatrix[i][16]);
      salesTerritory.addCountry(thisCountry);
      // Add country to the TMs
      salesTerritory.findRmByName(btdMatrix[i][17]).addCountry(salesTerritory.findCountryByName(btdMatrix[i][16]));
      salesTerritory.findTmByName(btdMatrix[i][17]).addCountry(salesTerritory.findCountryByName(btdMatrix[i][16]));
      salesTerritory.findTmByName(btdMatrix[i][18]).addCountry(salesTerritory.findCountryByName(btdMatrix[i][16]));
      salesTerritory.findTmByName(btdMatrix[i][19]).addCountry(salesTerritory.findCountryByName(btdMatrix[i][16]));
      // Dealer
      Dealer thisDealer = new Dealer();
      thisDealer.code(btdMatrix[i][1]);
      thisDealer.setName(btdMatrix[i][15]);
      thisDealer.country(salesTerritory.findCountryByName(btdMatrix[i][16]));
      thisDealer.rm(salesTerritory.findRmByName(btdMatrix[i][17]));
      thisDealer.tmCB(salesTerritory.findTmByName(btdMatrix[i][18]));
      thisDealer.tmWHE(salesTerritory.findTmByName(btdMatrix[i][19]));
      salesTerritory.addDealer(thisDealer);
      // Add the dealer to the country
      salesTerritory.findCountryByName(btdMatrix[i][16]).addDealer(salesTerritory.findDealerByCode(btdMatrix[i][1]));
    }
    return salesTerritory;
  }
}
