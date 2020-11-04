import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConcurrentSchemerComponent } from './concurrent-schemer.component';

describe('ConcurrentSchemerComponent', () => {
  let component: ConcurrentSchemerComponent;
  let fixture: ComponentFixture<ConcurrentSchemerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConcurrentSchemerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConcurrentSchemerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
