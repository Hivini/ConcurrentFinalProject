import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { AppComponent } from '../app.component';

@Injectable({
  providedIn: 'root',
})
export class SocketioService {
  stompClient: any;
  topic: string = '/topic/greetings';
  appComponent: AppComponent;

  constructor(appComponent: AppComponent) {
    this.appComponent = appComponent;
  }

  public connect(){
    console.log('%c Initializing socket connection.','color: #22a322');
    let webSocket = new SockJS(environment.WEB_SOCKET_ENDPOINT);
    this.stompClient = Stomp.over(webSocket);
    const _this = this;
    _this.stompClient.connect({}, (frame)=>{
      _this.stompClient.subscribe(_this.topic, (event)=>{
        _this.onMessageRecieved(event);
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

  onMessageRecieved(message) {
    console.log("Message Recieved from Server :: " + message);
    this.appComponent.handleMessage(JSON.stringify(message.body));
  }
}
