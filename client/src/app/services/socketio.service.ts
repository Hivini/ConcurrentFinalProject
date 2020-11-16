import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { io } from 'socket.io-client';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class SocketioService {
  public socket;

  constructor() {
    this.socket = io(environment.SOCKET_ENDPOINT)
  }

  public sendSchemeOperation(schemeOperation: string, id: number) : void{
    this.socket.emit('scheme-operation',schemeOperation,id)
  }

  public getProgress = () => {
    return new Observable((observer)=>{
      this.socket.on('progress-notification', (progressObj)=>{
        observer.next(progressObj);
      })
    })
  }

  public getResult = () => {
    return new Observable((observer)=>{
      this.socket.on('new-result', (resultObj)=>{
        observer.next(resultObj);
      })
    })
  }
}
