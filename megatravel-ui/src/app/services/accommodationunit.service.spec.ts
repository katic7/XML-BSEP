import { TestBed } from '@angular/core/testing';

import { AccommodationunitService } from './accommodationunit.service';

describe('AccommodationunitService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AccommodationunitService = TestBed.get(AccommodationunitService);
    expect(service).toBeTruthy();
  });
});
