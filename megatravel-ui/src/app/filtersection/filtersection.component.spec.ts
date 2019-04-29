import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FiltersectionComponent } from './filtersection.component';

describe('FiltersectionComponent', () => {
  let component: FiltersectionComponent;
  let fixture: ComponentFixture<FiltersectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FiltersectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FiltersectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
