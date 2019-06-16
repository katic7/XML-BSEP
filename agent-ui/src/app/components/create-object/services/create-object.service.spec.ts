import { TestBed } from '@angular/core/testing';

import { CreateObjectService } from './create-object.service';

describe('CreateObjectService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CreateObjectService = TestBed.get(CreateObjectService);
    expect(service).toBeTruthy();
  });
});
