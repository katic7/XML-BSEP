import { TestBed } from '@angular/core/testing';

import { AddressServiceService } from './address-service.service';

describe('AddressServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddressServiceService = TestBed.get(AddressServiceService);
    expect(service).toBeTruthy();
  });
});
