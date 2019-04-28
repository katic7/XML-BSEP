import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SidesearchformComponent } from './sidesearchform.component';

describe('SidesearchformComponent', () => {
  let component: SidesearchformComponent;
  let fixture: ComponentFixture<SidesearchformComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SidesearchformComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SidesearchformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
