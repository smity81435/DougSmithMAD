//
//  ViewController.swift
//  midterm
//
//  Created by Douglas Smith on 10/18/18.
//  Copyright © 2018 Douglas Smith. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet weak var wotime: UITextField!
  
    @IBOutlet weak var wonum: UILabel!
    @IBOutlet weak var distance: UILabel!
    @IBOutlet weak var units: UILabel!
    @IBOutlet weak var calburned: UILabel!
    @IBOutlet weak var woimg: UIImageView!
    @IBOutlet weak var sliderone: UISlider!
    @IBAction func slider(_ sender: UISlider) {
        let wopw = Float(sender.value)
        wonum.text = String(wopw)
    }
    @IBOutlet weak var weekSwitch: UISwitch!
    @IBAction func weeklyswitch(_ sender: Any) {
        updateWeek()
    }
    
    @IBOutlet weak var wotype: UISegmentedControl!
    @IBAction func segone(_ sender: Any) {
        updateWO()
    }
    
    
    @IBAction func wobutton(_ sender: Any) {
        wocalc(wocf:1)
    }
//    RESIGN KEYBOARD
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
//    WORKOUT CALCULATION
    func wocalc(wocf:Int){
        
        var dist, time, cal:Float
        if wotime.text!.isEmpty{
            time = 0.0
        } else {
            time = Float(wotime.text!)!
        }
        //alert
        
        if time >= 30 {
            if wocf == 1{
            dist = time/10
            cal = time*10
            distance.text = String(dist)
            calburned.text = String(cal)
            }else if wocf == 2{
                dist = time/15
                cal = time*8.5
                distance.text = String(dist)
                calburned.text = String(cal)
            }else if wocf == 3 {
                dist = time/30
                cal = time*7
                distance.text = String(dist)
                calburned.text = String(cal)
            }else if wocf == 4{
                dist = (time/30)*sliderone.value
                cal = (time*7)*sliderone.value
                distance.text = String(dist)
                calburned.text = String(cal)
            }
            
        }else{
            //create an alert controller
            let alert=UIAlertController(title: "Warning", message: "You Heinous Creature! WORKOUT LONGER!", preferredStyle: UIAlertControllerStyle.alert)
            let cancelAction=UIAlertAction(title: "Cancel", style: UIAlertActionStyle.cancel, handler: nil)
            alert.addAction(cancelAction)
            let okAction=UIAlertAction(title: "ok", style: UIAlertActionStyle.default, handler: {action in
                self.wotime.text="30"
                self.wocalc(wocf:1)
            })
            alert.addAction(okAction)
            present(alert, animated:true,completion: nil)
        } //end else
        

        
    }
    override func viewDidLoad() {
        wotime.delegate=self
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
//    WEEKLY ON
    func updateWeek(){
        if weekSwitch.isOn{
            wocalc(wocf: 4)
        }else {
           
        }
        
    }
//    UPDATE WORKOUT
    func updateWO(){
        
        if wotype.selectedSegmentIndex == 0 {
            units.text = "Miles"
            woimg.image = UIImage(named: "run")
            wocalc(wocf:1)
        }else if wotype.selectedSegmentIndex == 1 {
            units.text = "Miles"
            woimg.image = UIImage(named: "swim")
            wocalc(wocf:2)
            
        }else if wotype.selectedSegmentIndex == 2 {
            units.text = "Miles"
            woimg.image = UIImage(named: "bike")
            wocalc(wocf:3)
        }
    }



}

