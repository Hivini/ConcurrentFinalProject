import { Component } from '@angular/core';
import { SocketioService } from './services/socketio.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'lang-project';
  greeting: string;
  socketIoService: SocketioService;

  ngOnInit(){
    this.socketIoService = new SocketioService(new AppComponent)
  }

  connect(){
    this.socketIoService.connect();
  }

  disconnect(){
    this.socketIoService.disconnect();
  }

  handleMessage(message){
    this.greeting = message;
  }

}

