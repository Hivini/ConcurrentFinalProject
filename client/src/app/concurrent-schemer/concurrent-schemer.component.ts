import { Component, OnInit, ViewChild } from '@angular/core';
import { SocketService } from '../services/socket.service';

@Component({
  selector: 'app-concurrent-schemer',
  templateUrl: './concurrent-schemer.component.html',
  styleUrls: ['./concurrent-schemer.component.scss']
})
export class ConcurrentSchemerComponent implements OnInit {

  public prodNum: number = 5
  public consNum: number = 5
  public bufSize: number = 20
  public currenttBufSize: number = 0
  public waitTime: number = 2000
  public valueRange: Array<number> = [0,9]
  public operators = [
    { label: 'Sum', value: '+', checked: true },
    { label: 'Multiplication', value: '*', checked: true },
    { label: 'Division', value: '/', checked: true },
    { label: 'Substraction', value: '-', checked: true },
  ];
  public operations: Map<string,any> = new Map()
  public canStop: boolean

  constructor(
    private socketService : SocketService
  ) { }


  ngOnInit(): void {
    this.socketService.connect();
    this.socketService.resultsSubject.subscribe((event)=>{
      let parsedObject: any = JSON.parse(event.body)
      this.operations.set(parsedObject.id,{'operation': parsedObject.operation, 'result': parsedObject.result})

      if(this.getProgress()==100){
        this.canStop=false
      }
    })
    this.socketService.stopSubject.subscribe((event)=>{
      console.log("stopSubject",event)
    })
  }

  getProgress(){
    let solved = 0
    this.operations.forEach((value,key)=>{
      if(value.result){
        solved++
      }
    })
    return Math.round(solved * 100 / this.currenttBufSize)
  }

  getOperators(){
    let toReturn: string = "";
    for(let operator of  this.operators){
      if(operator.checked){
        toReturn = toReturn + operator.value + ","
      }
    }
    return toReturn.slice(0,-1)
  }

  start(){
    this.operations.clear()
    this.canStop = true;
    this.getOperators()
    this.currenttBufSize = this.bufSize
    this.socketService.send(`${this.consNum},${this.prodNum},${this.bufSize},${this.valueRange[0]},${this.valueRange[1]},${this.waitTime},${this.getOperators()}`)
  }

  stop(){
    this.canStop = false;
    this.socketService.stop()
  }
}
