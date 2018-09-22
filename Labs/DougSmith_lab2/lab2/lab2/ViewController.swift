//
//  ViewController.swift
//  lab2
//
//  Created by Douglas Smith on 9/21/18.
//  Copyright © 2018 Douglas Smith. All rights reserved.
//

import UIKit

class ViewController: UIViewController {


    @IBOutlet weak var fontSizeNumberLabel: UILabel!
    @IBOutlet weak var iscased: UILabel!
    @IBOutlet weak var color: UILabel!
    @IBOutlet weak var slideThis: UISlider!
    @IBOutlet weak var capitalSwitch: UISwitch!
    @IBOutlet weak var tessImg: UIImageView!
    @IBOutlet weak var tessLab: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    @IBAction func switchOne(_ sender: UISwitch) {
        updateCaps()
    }
    
    @IBAction func sliderOne(_ sender: UISlider) {
        let fontSize=sender.value
        fontSizeNumberLabel.text=String(format: "%.0f", fontSize)
        let fontSizeCGFloat=CGFloat(fontSize)
        tessLab.font=UIFont.systemFont(ofSize: fontSizeCGFloat)
        
    }
    
    @IBOutlet weak var imageControl: UISegmentedControl!
    @IBAction func segOne(_ sender: UISegmentedControl) {
            updateImage()
            updateCaps()
    }
    func updateCaps(){
        if capitalSwitch.isOn{
            tessLab.text = tessLab.text?.uppercased()
            iscased.text = "UPPERS"
        }else {
            tessLab.text = tessLab.text?.lowercased()
            iscased.text = "LOWERS"
        }
        
    }
    func updateImage(){
        if imageControl.selectedSegmentIndex == 0 {
            tessLab.text = "TESSELATE IT!"
            tessLab.textColor = UIColor.red
            tessImg.image = UIImage(named: "1")
            color.text = "red"
            
            
        }else if imageControl.selectedSegmentIndex == 1 {
            tessLab.text = "OOOH WOW!"
            tessImg.image = UIImage(named: "2")
            tessLab.textColor = UIColor.green
            color.text = "green"
            
        }else if imageControl.selectedSegmentIndex == 2 {
            tessLab.text = "GEE WHIZ!"
            tessImg.image = UIImage(named: "3")
            tessLab.textColor = UIColor.blue
            color.text = "blue"
            
        }else if imageControl.selectedSegmentIndex == 3 {
            tessLab.text = "HOLY SHUCKS!"
            tessImg.image = UIImage(named: "4")
            tessLab.textColor = UIColor.purple
            color.text = "purple"
            
        }
    }
    

}

