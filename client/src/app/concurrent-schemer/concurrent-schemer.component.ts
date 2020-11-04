import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-concurrent-schemer',
  templateUrl: './concurrent-schemer.component.html',
  styleUrls: ['./concurrent-schemer.component.scss']
})
export class ConcurrentSchemerComponent implements OnInit {
  public prodNum: number = 5
  public consNum: number = 5
  public waitTime: number = 5000
  public valueRange: Array<number> = [0,9]
  public operators = [
    { label: 'Sum', value: 'sum', checked: true },
    { label: 'Multiplication', value: 'mult', checked: true },
    { label: 'Division', value: 'div', checked: true },
    { label: 'Substraction', value: 'sub', checked: true },
  ];
  constructor() { }


  ngOnInit(): void {
  }

}
