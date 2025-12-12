package nq.io.input;

import nq.catalogue.*;
import java.util.*;

public class OptionAttributeProcessor {
  public OptionAttributeProcessor() {
  }

  public Option processRecord(Hashtable data, Option thisOption) {
    // MAST SUPERGROUP
    if (data.get("MHT") != null && data.get("MHT").toString().length() > 1) {
      thisOption.addAttribute("Mast Height", data.get("MHT").toString());
    }
    if (data.get("STG") != null && data.get("STG").toString().length() > 1) {
      thisOption.addAttribute("Mast Stages", data.get("STG").toString());
    }
    if (data.get("LTY") != null && data.get("LTY").toString().length() > 1) {
      thisOption.addAttribute("Lift Type", data.get("LTY").toString());
    }
    if (data.get("get") != null && data.get("FCL").toString().length() > 1) {
      thisOption.addAttribute("Forks Class", data.get("FCL").toString());

      // BATTERY SUPERGROUP
    }
    if (data.get("BAM") != null && data.get("BAM").toString().length() > 1) {
      thisOption.addAttribute("Battery Amps", data.get("BAM").toString());
    }
    if (data.get("BHZ") != null && data.get("BHZ").toString().length() > 1) {
      thisOption.addAttribute("Battery Hzs", data.get("BHZ").toString());
    }
    if (data.get("BLT") != null && data.get("BLT").toString().length() > 1) {
      thisOption.addAttribute("Battery Standard Letter", data.get("BLT").toString());
    }
    if (data.get("BVT") != null && data.get("BVT").toString().length() > 1) {
      thisOption.addAttribute("Battery Volts", data.get("BVT").toString());
    }
    if (data.get("CPH") != null && data.get("CPH").toString().length() > 1) {
      thisOption.addAttribute("Charger Phases", data.get("CPH").toString());
    }
    if (data.get("CHR") != null && data.get("CHR").toString().length() > 1) {
      thisOption.addAttribute("Charging Hours", data.get("CHR").toString());
    }
    if (data.get("BST") != null && data.get("BST").toString().length() > 1) {
      thisOption.addAttribute("Battery Standard", data.get("BST").toString());
    }
    if (data.get("AQU") != null && data.get("AQU").toString().length() > 1) {
      thisOption.addAttribute("Battery Type", data.get("AQU").toString());
    }
    if (data.get("BCH") != null && data.get("BCH").toString().length() > 1) {
      thisOption.addAttribute("Batt Comp Height", data.get("BCH").toString());

      // ENGINE SUPERGROUP
    }
    if (data.get("FTP") != null && data.get("FTP").toString().length() > 1) {
      thisOption.addAttribute("Fuel Type", data.get("FTP").toString());
    }
    if (data.get("ELT") != null && data.get("ELT").toString().length() > 1) {
      thisOption.addAttribute("Engine Litres", data.get("ELT").toString());
    }
    if (data.get("EMK") != null && data.get("EMK").toString().length() > 1) {
      thisOption.addAttribute("Engine Make", data.get("EMK").toString());
    }
    if (data.get("BRK") != null && data.get("BRK").toString().length() > 1) {
      thisOption.addAttribute("Brakes", data.get("BRK").toString());

      // CARRIAGE SUPERGROUP
    }
    if (data.get("CHT") != null && data.get("CHT").toString().length() > 1) {
      thisOption.addAttribute("Carriage Height", data.get("CHT").toString());
    }
    if (data.get("STG") != null && data.get("STG").toString().length() > 1) {
      thisOption.addAttribute("Mast Stages", data.get("STG").toString());
    }
    if (data.get("LTY") != null && data.get("LTY").toString().length() > 1) {
      thisOption.addAttribute("Lift Type", data.get("LTY").toString());
    }
    if (data.get("FCL") != null && data.get("FCL").toString().length() > 1) {
      thisOption.addAttribute("Forks Class", data.get("FCL").toString());

      // HYDRAULICS SUPERGROUP
    }
    if (data.get("LEV") != null && data.get("LEV").toString().length() > 1) {
      thisOption.addAttribute("Levers", data.get("LEV").toString());
    }
    if (data.get("FTN") != null && data.get("FTN").toString().length() > 1) {
      thisOption.addAttribute("Functions", data.get("FTN").toString());
    }
    if (data.get("HYD") != null && data.get("HYD").toString().length() > 1) {
      thisOption.addAttribute("Hydraulics", data.get("HYD").toString());
    }
    if (data.get("CLA") != null && data.get("CLA").toString().length() > 1) {
      thisOption.addAttribute("Clamping", data.get("CLA").toString());
    }
    if (data.get("VWY") != null && data.get("VWY").toString().length() > 1) {
      thisOption.addAttribute("Valve Ways", data.get("VWY").toString());

    }
    return thisOption;
  }

}
