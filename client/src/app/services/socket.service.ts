import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

@Injectable({
  providedIn: 'root',
})
export class SocketService {
  stompClient: any;
  topic: string = '/topic/greetings';
  public messagesSubject : Subject<any> =  new Subject()

  public connect(){
    console.log('%c Initializing socket connection.','color: #22a322');
    let webSocket = new SockJS(environment.WEB_SOCKET_ENDPOINT);
    this.stompClient = Stomp.over(webSocket);
    const _this = this;
    _this.stompClient.connect({}, (frame)=>{
      _this.stompClient.subscribe(_this.topic, (event)=>{
        this.messagesSubject.next(event);
      })
    }, _this.errorCallback)
  }

  public disconnect() {
    if (this.stompClient !== null) {
        this.stompClient.disconnect();
    }
    console.log("Disconnected");
  }

  public errorCallback(error) {
    console.log("errorCallBack -> " + error)
    setTimeout(() => {
        this.connect();
    }, 5000);
  }

  public send(message){
    console.log("calling logout api via web socket");
    this.stompClient.send("/app/hello", {}, JSON.stringify(message));
  }

}
