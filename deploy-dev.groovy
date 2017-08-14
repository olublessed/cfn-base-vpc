aws {
    profileName = artifacts3.profileName
    region = 'us-east-2'
}

cloudFormation {
    templateFile = project.file('build/cloudformation/deploy/test.yaml')
    stackName = (artifacts3.stackName) ? artifacts3.stackName : 'vpc-test'
    capabilityIam true
    onFailure 'ROLLBACK' // DO_NOTHING | ROLLBACK | DELETE
    conventionMapping.stackParams = {
        return stackParams = [
            ProjectName: 'VPC Test',
            AvailabilityZone1: 'us-east-2a',
            AvailabilityZone2: 'us-east-2b',
            BastionAllowedIPs: '0.0.0.0/0',
            pEC2KeyPairBastion: 'raptor',
            pEC2KeyPair: 'raptor'
        ]
    }
}