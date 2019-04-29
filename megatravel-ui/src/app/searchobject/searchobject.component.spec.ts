import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchobjectComponent } from './searchobject.component';

describe('SearchobjectComponent', () => {
  let component: SearchobjectComponent;
  let fixture: ComponentFixture<SearchobjectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchobjectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchobjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
